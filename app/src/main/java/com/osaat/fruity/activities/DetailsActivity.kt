package com.osaat.fruity.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.osaat.fruity.R
import com.osaat.fruity.utils.ValueConverter
import com.osaat.fruity.viewmodels.FruitItemViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val FRUIT_EXTRA = "fruit_extra"
    }

    private lateinit var valueConverter: ValueConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        valueConverter = ValueConverter()

        val fruitItemModel = intent.extras?.getParcelable<FruitItemViewModel>(FRUIT_EXTRA)
        fruitItemModel?.let {
            supportActionBar?.title = String.format(getString(R.string.title_activity_details), it.fruitType.capitalize())
            price_view.text = String.format(getString(R.string.price_template), valueConverter.convertPriceFromPenceToPounds(it.price!!))
            weight_view.text = String.format(getString(R.string.weight_template), valueConverter.convertWeightFromGramsToKilos(it.weight!!))
        }
    }
}
