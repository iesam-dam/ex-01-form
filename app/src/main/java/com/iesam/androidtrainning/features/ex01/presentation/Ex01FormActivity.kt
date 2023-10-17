package com.iesam.androidtrainning.features.ex01.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.iesam.androidtrainning.R
import com.iesam.androidtrainning.app.ErrorApp
import com.iesam.androidtrainning.app.extensions.hide
import com.iesam.androidtrainning.app.extensions.visible
import com.iesam.androidtrainning.databinding.ActivityEx01FormBinding
import com.iesam.androidtrainning.features.ex01.data.UserDataRepository
import com.iesam.androidtrainning.features.ex01.data.local.XmlLocalDataSource
import com.iesam.androidtrainning.features.ex01.domain.GetUserUseCase
import com.iesam.androidtrainning.features.ex01.domain.SaveUserUseCase
import com.iesam.androidtrainning.features.ex01.domain.User

class Ex01FormActivity : AppCompatActivity() {

    lateinit var binding: ActivityEx01FormBinding

    //Para usar esta creación se ha añadido:  implementation "androidx.activity:activity-ktx:1.7.2"
    val viewModel: Ex01FormViewModel by lazy {
        Ex01FormViewModel(
            SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupView()
        setupObservers()
        viewModel.loadUser()
    }

    private fun bindView() {
        binding = ActivityEx01FormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupView() {
        val actionButton = findViewById<Button>(R.id.action_save)
        actionButton.setOnClickListener {
            viewModel.saveUser(getNameInput(), getSurnameInput(), getAgeInput())
        }
        binding.actionSave.setOnClickListener {
            //lo que sea
        }
        //findViewById<ViewGroup>(R.id.row_2).findViewById<TextView>(R.id.label_username)
        //findViewById<ViewGroup>(R.id.row_3).findViewById<TextView>(R.id.label_username)
        findViewById<ViewGroup>(R.id.row_4).findViewById<TextView>(R.id.label_username)
    }

    private fun getNameInput(): String =
        findViewById<EditText>(R.id.input_name).text.toString()

    private fun getSurnameInput(): String =
        findViewById<EditText>(R.id.input_surname).text.toString()

    private fun getAgeInput(): String =
        findViewById<EditText>(R.id.input_age).text.toString()

    private fun setupObservers() {
        val observer = Observer<Ex01FormViewModel.UiState> {
            //Código al notificar el observador
            if (it.isLoading) {
                //Muestro el loading
                showLoading()
            } else {
                //Oculto el loading
                hideLoading()
            }
            it.errorApp?.let {
                showError(it)
            }
            it.user?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun showError(error: ErrorApp) {
        binding.viewError.layoutError.visible()
        binding.layoutForm.hide()
        when (error) {
            ErrorApp.UnknowError -> binding.viewError.messageError.text =
                getString(R.string.label_unknown_error)
        }
    }

    private fun showLoading() {
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading() {
        binding.skeletonLayout.showOriginal()
        binding.layoutForm.visible()
    }

    private fun bindData(user: User) {
        setNameInput(user.username)
        setSurnameInput(user.surname)
        setAgeInput(user.age)
    }

    private fun setNameInput(name: String) {
        findViewById<EditText>(R.id.input_name).setText(name)
    }

    private fun setSurnameInput(surname: String) {
        findViewById<EditText>(R.id.input_surname).setText(surname)
    }

    private fun setAgeInput(age: String) {
        findViewById<EditText>(R.id.input_age).setText(age)
        findViewById<EditText>(R.id.input_age).visibility = View.VISIBLE
        findViewById<EditText>(R.id.input_age).visibility = View.GONE
        findViewById<EditText>(R.id.input_age).visibility = View.INVISIBLE
    }

    fun bindData(users: List<User>) {
        if (users.size == 1) {
            findViewById<ViewGroup>(R.id.row_1).apply {
                findViewById<TextView>(R.id.label_username).text = users.get(0).username
                findViewById<TextView>(R.id.label_surname).text = users.get(0).surname
                findViewById<TextView>(R.id.action_delete).setOnClickListener {
                    //viewModel.deleteUser(users.get(0).id)
                }
            }
            binding.apply {
                row1.labelUsername.text = "Chema"
                row2.labelUsername.text = "asd"
            }
        }
        if (users.size == 2) {

        }
    }
}