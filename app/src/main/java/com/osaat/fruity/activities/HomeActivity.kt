package com.osaat.fruity.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.osaat.fruity.R
import com.osaat.fruity.adapter.HomeAdapter
import com.osaat.fruity.adapter.OnItemClickListener
import com.osaat.fruity.api.FruitsApi
import com.osaat.fruity.api.retrofit.MyRetrofitClient
import com.osaat.fruity.converters.FruitsConverter
import com.osaat.fruity.mvp.HomePresenter
import com.osaat.fruity.mvp.HomePresenterImp
import com.osaat.fruity.services.FruitsService
import com.osaat.fruity.viewmodels.FruitItemViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomePresenter.View, OnItemClickListener<FruitItemViewModel> {

    private lateinit var presenter: HomePresenter
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initScreenAdapter()

        val service = FruitsService(
            MyRetrofitClient.api(FruitsApi::class.java),
            FruitsConverter(),
            AndroidSchedulers.mainThread(),
            Schedulers.io()
        )
        presenter = HomePresenterImp(this, fruitsService = service)
        presenter.fetchFruits()
    }

    private fun initScreenAdapter() {
        homeAdapter = HomeAdapter(this)
        with(recycler_view) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> presenter.fetchFruits()
            else -> {
                //ignore
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onItemClick(view: View, selectedItem: FruitItemViewModel) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.FRUIT_EXTRA, selectedItem)
        startActivity(intent)
    }

    override fun showFruitsResult(result: List<FruitItemViewModel>) {
        homeAdapter.setData(result)
    }

    override fun showLoader(show: Boolean) {
        progress_indicator.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
