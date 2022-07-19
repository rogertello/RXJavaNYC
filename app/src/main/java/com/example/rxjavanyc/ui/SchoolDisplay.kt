package com.example.rxjavanyc.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavanyc.data.model.NYCSchoolResponse
import com.example.rxjavanyc.databinding.SchoolDisplayFragmentLayoutBinding
import com.example.rxjavanyc.repository.Repository
import com.example.rxjavanyc.repository.state.SchoolResponse
import com.example.rxjavanyc.repository.state.UIState
import com.example.rxjavanyc.ui.viewmodel.SchoolViewModel
import com.example.rxjavanyc.ui.viewmodel.SchoolViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SchoolDisplay: Fragment(), ListenerInterface.ListClickEvent {

    @Inject
    lateinit var repository: Repository
    @Inject
   lateinit var schoolViewModelProvider: SchoolViewModelProvider
    private lateinit var binding: SchoolDisplayFragmentLayoutBinding
    private lateinit var viewModel: SchoolViewModel
    private lateinit var adapter: SchoolAdapter
    private lateinit var listener: ListenerInterface
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener =
            if (context is ListenerInterface) context else throw ExceptionInInitializerError("Incorrect Host Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SchoolDisplayFragmentLayoutBinding.inflate(inflater,container,false)
        val view: View = binding.root
        binding.schoolList.layoutManager = GridLayoutManager(
            context,
            3,
            RecyclerView.VERTICAL,
            false
        )
        initObservables()
        return view
    }

    private fun initObservables() {
        viewModel = ViewModelProvider(this, schoolViewModelProvider)[SchoolViewModel::class.java]
        viewModel.schoolState.observe(viewLifecycleOwner) { uiState: UIState ->
            processUIState(
                uiState
            )
        }
    }

    private fun processUIState(uiState: UIState) {
        if (uiState is SchoolResponse) updateAdapter(uiState.data) else if (uiState is Error) uiState.errorMessage?.let {
            showError(
                it
            )
        }
    }

    private fun showError(errorMessage: String) {}

    private fun updateAdapter(data: List<NYCSchoolResponse>) {
        adapter = SchoolAdapter(data, this)
        binding.schoolList.adapter = adapter
    }

    override fun clickDetails(dbn: String?, name: String?, loc: String?, email: String?, phone: String?) {
        listener.openDetails(dbn, name, loc, email, phone)
    }

}
