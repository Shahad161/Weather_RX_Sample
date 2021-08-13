package com.ibareq.weathersample.ui

import android.view.LayoutInflater
import com.ibareq.weathersample.model.Status
import com.ibareq.weathersample.model.response.WeatherResponse
import com.ibareq.weathersample.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import com.ibareq.weathersample.presenter.MainPresenter
import com.ibareq.weathersample.ui.ViewVisibility.hide
import com.ibareq.weathersample.ui.ViewVisibility.show
import io.reactivex.rxjava3.core.Observable
import kotlin.math.roundToInt

class MainActivity : BaseActivity<ActivityMainBinding>(), IMainView {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val presenter = MainPresenter(this)

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding = ActivityMainBinding::inflate

    override fun setup(){}

    override fun addCallBack() {
        binding?.buttonSearch?.setOnClickListener {
            getWeatherForCity(binding!!.inputCityName.text.toString())
        }
    }

    private fun getWeatherForCity(cityName: String){
        presenter.getWeatherForCity(cityName)
    }

    override fun onUserInfoSuccess(weatherRepository: Observable<Status<WeatherResponse>>) {
        disposable.add(weatherRepository
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onWeatherResult)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun onWeatherResult(response: Status<WeatherResponse>){
        hideAllViews()
        when(response){
            is Status.Error -> {
                binding?.imageError?.show()
            }
            is Status.Loading -> {
                binding?.progressLoading?.show()
            }
            is Status.Success -> {
                presenter.bindData(response)
            }
        }
    }

    override fun bindData(data: WeatherResponse) {
        binding?.textMaxTemp?.run {
            show()
            val maxTemp = data.consolidatedWeather[0].maxTemp.roundToInt().toString()
            val cityName = data.title
            text = "$cityName: $maxTemp"
        }
    }

    private fun hideAllViews(){
        binding?.run {
            progressLoading.hide()
            textMaxTemp.hide()
            imageError.hide()
        }
    }

}

