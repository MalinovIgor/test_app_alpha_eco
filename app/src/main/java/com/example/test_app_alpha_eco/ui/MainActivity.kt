package com.example.test_app_alpha_eco.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.test_app_alpha_eco.R
import com.example.test_app_alpha_eco.databinding.ActivityMainBinding
import com.example.test_app_alpha_eco.domain.models.Card
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!
    private val viewModel by viewModel<CardDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        render(CardState.NormalCondition)

        binding.btClear.setOnClickListener {
            binding.etCardNumber.text.clear()
            viewModel.clearCurrentCardNumber()
        }

        binding.etCardNumber.addTextChangedListener { s ->
            if (s.isNullOrEmpty()) {
                showEmpty()
            }
            if (!s.isNullOrBlank()) {
                hidePlaceholders()
                viewModel.setCardNumber(s.toString())
            }
        }

        viewModel.getCardScreenStateLiveData.observe(this) {
            render(it)
        }

        binding.btSearch.setOnClickListener {

            viewModel.getCardResources(
                viewModel.currentCardNumber ?: return@setOnClickListener
            )
        }

    }

    private fun render(state: CardState) {
        when (state) {
            is CardState.Loading -> showLoading()
            is CardState.ServerError -> showError()
            is CardState.BadRequest -> badRequest()
            is CardState.NetworkError -> networkError()
            is CardState.TooManyRequests -> showTooManyRequests()
            is CardState.Content -> showContent(state.item)
            is CardState.WrongInput -> showWrongInput()
            is CardState.NormalCondition -> showNormalCondition()
        }
    }

    private fun showNormalCondition() {
        with(binding) {
            progressBar.isVisible = false
            etCardNumber.setTextColor(getResources().getColorStateList(R.color.white))
            tvNotFound.isVisible = false
            tvErrorServer.isVisible = false
            tvErrorInternet.isVisible = false
        }
    }

    private fun showWrongInput() {
        with(binding) {
            progressBar.isVisible = false
            etCardNumber.setTextColor(getResources().getColorStateList(R.color.red))
            tvNotFound.isVisible = false
            tvErrorServer.isVisible = false
            tvErrorInternet.isVisible = false
        }
    }

    private fun showContent(item: Card) {
        with(binding) {
            llCardData.isVisible = true
            progressBar.isVisible = false
            tvCountry.text = item.country?.name
            tvCoordinats.text =
                item.country?.latitude.toString() + item.country?.longitude.toString()
            tvBankName.text = item.bank?.name
            tvCardType.text = item.type
        }
    }

    private fun showLoading() {
        with(binding) {
            progressBar.isVisible = true
            llCardData.isVisible = false
        }
    }

    private fun showError() {
        with(binding) {
            progressBar.isVisible = false
            tvNotFound.isVisible = true
            tvErrorServer.isVisible = true
            tvErrorInternet.isVisible = false
        }
    }

    private fun badRequest() {
        with(binding) {
            progressBar.isVisible = false
            llCardData.isVisible = false
            tvNotFound.isVisible = true
            tvErrorServer.isVisible = true
            tvErrorInternet.isVisible = false
        }
    }

    private fun networkError() {
        with(binding) {
            progressBar.isVisible = false
            llCardData.isVisible = false
            tvErrorServer.isVisible = false
            tvErrorInternet.isVisible = true
            tvNotFound.isVisible = false
            tooManyRequests.isVisible = false
        }
    }

    private fun showTooManyRequests() {
        with(binding) {
            progressBar.isVisible = false
            llCardData.isVisible = false
            tvNotFound.isVisible = false
            tooManyRequests.isVisible = true
            tvErrorServer.isVisible = false
            tvErrorInternet.isVisible = false
        }
    }

    private fun showEmpty() {
        with(binding) {
            progressBar.isVisible = false
            llCardData.isVisible = false
            tvNotFound.isVisible = false
            tvErrorServer.isVisible = false
            tvErrorInternet.isVisible = false
        }
    }

    private fun hidePlaceholders() {
        with(binding) {
            progressBar.isVisible = false
            tvNotFound.isVisible = false
            tvErrorServer.isVisible = false
            tvErrorInternet.isVisible = false
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}