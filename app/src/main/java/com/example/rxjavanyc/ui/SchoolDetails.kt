package com.example.rxjavanyc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rxjavanyc.R
import com.example.rxjavanyc.data.model.NYCSATResponse
import com.example.rxjavanyc.databinding.SchoolDetailsFragmentLayoutBinding
import com.example.rxjavanyc.repository.Repository
import com.example.rxjavanyc.repository.state.SATResponse
import com.example.rxjavanyc.repository.state.UIState
import com.example.rxjavanyc.ui.viewmodel.SchoolViewModel
import com.example.rxjavanyc.ui.viewmodel.SchoolViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SchoolDetails : Fragment() {

    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var schoolViewModelProvider: SchoolViewModelProvider

    private lateinit var binding:  SchoolDetailsFragmentLayoutBinding
    private lateinit var viewModel: SchoolViewModel

    private var schoolNameStr: String? = null
    private var schoolLocStr: String? = null
    private var schoolEmailStr: String? = null
    private var schoolPhoneStr: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SchoolDetailsFragmentLayoutBinding.inflate(inflater, container, false)
        val view: View = binding.root
        initObservables()
        if (requireArguments().getString(KEY_DBN) != null) viewModel.getSatDetails(
            requireArguments().getString(KEY_DBN)
        )

        if (requireArguments().getString(KEY_NAME) != null) schoolNameStr = requireArguments().getString(KEY_NAME)
        if (requireArguments().getString(KEY_LOC) != null) schoolLocStr = requireArguments().getString(KEY_LOC)!!
            .substring(
                0, requireArguments().getString(KEY_LOC)!!
                    .indexOf('(')
            )
        if (requireArguments().getString(KEY_PHONE) != null) schoolPhoneStr = requireArguments().getString(
            KEY_PHONE
        )
        if (requireArguments().getString(KEY_EMAIL) != null) schoolEmailStr = requireArguments().getString(
            KEY_EMAIL
        )
        return view
    }

    private fun initObservables() {
        viewModel =
            schoolViewModelProvider.let {
                ViewModelProvider(this,
                    it
                ).get(SchoolViewModel::class.java)
            }
        viewModel.schoolState.observe(viewLifecycleOwner) { uiState: UIState ->
            updateUI(
                uiState
            )
        }
    }

    private fun updateUI(uiState: UIState) {
        if (uiState is SATResponse)
            {
            updateView(uiState.data)
            }
        else if (uiState is Error) uiState.errorMessage?.let {
            showError(it)
        }
    }

    private fun showError(errorMessage: String) {}

    private fun updateView(data: NYCSATResponse) {
        val takers = getString(R.string.sat_takers) + data.num_of_sat_test_takers
        val math = getString(R.string.sat_math) + data.sat_math_avg_score
        val read = getString(R.string.sat_read) + data.sat_critical_reading_avg_score
        val write = getString(R.string.sat_write) + data.sat_writing_avg_score
        binding.schoolName.text = schoolNameStr


        binding.schoolLocation.text = schoolLocStr
        binding.schoolEmail.text = schoolEmailStr
        binding.schoolPhone.text = schoolPhoneStr
        if (data.dbn == null ||
            data.school_name == null ||
            data.num_of_sat_test_takers == null ||
            data.sat_math_avg_score == null ||
            data.sat_writing_avg_score == null ||
            data.sat_critical_reading_avg_score == null) {
            binding.satDetails.setText(R.string.sat_na)
        } else {
            binding.satDetails.setText(R.string.sat_details)
            binding.schoolDetailsSatTakers.text = takers
            binding.schoolDetailsSatReading.text = read
            binding.schoolDetailsSatWriting.text = write
            binding.schoolDetailsSatMath.text = math
        }
    }

    companion object {
        private const val KEY_DBN = "KEY_DBN_SCHOOL_DETAILS"
        private const val KEY_NAME = "KEY_NAME_SCHOOL_DETAILS"
        private const val KEY_LOC = "KEY_LOC_SCHOOL_DETAILS"
        private const val KEY_PHONE = "KEY_PHONE_SCHOOL_DETAILS"
        private const val KEY_EMAIL = "KEY_EMAIL_SCHOOL_DETAILS"

        fun getInstance(
            dbn: String?,
            name: String?,
            loc: String?,
            email: String?,
            phone: String?
        ): Fragment {
            val fragment = SchoolDetails()
            val bundle = Bundle()
            bundle.putString(KEY_DBN, dbn)
            bundle.putString(KEY_NAME, name)
            bundle.putString(KEY_LOC, loc)
            bundle.putString(KEY_EMAIL, email)
            bundle.putString(KEY_PHONE, phone)
            fragment.arguments = bundle
            return fragment
        }
    }

}