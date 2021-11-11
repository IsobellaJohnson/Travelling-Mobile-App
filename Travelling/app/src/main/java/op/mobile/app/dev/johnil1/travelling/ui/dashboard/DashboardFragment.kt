package op.mobile.app.dev.johnil1.travelling.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.johnil1.travelling.R
import op.mobile.app.dev.johnil1.travelling.databinding.FragmentDashboardBinding
//import op.mobile.app.dev.johnil1.travelling.ui.home.HomeFragmentDirections
//import op.mobile.app.dev.johnil1.travelling.ui.dashboard.DashboardFragmentDirections


class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentDashboardBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dashboard,
            container,
            false
        )

        val viewModelFactory =
            /**
             * QuizViewModelFactory allows you to retrieve the Country object passed
             * from HomeFragment
             */
            DashboardViewModelFactory(
                DashboardFragmentArgs.fromBundle(requireArguments()).country
            )

        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(DashboardViewModel::class.java)

        Log.d("test", viewModel.country.toString())

        binding.apply{
            lifecycleOwner = viewLifecycleOwner
            dashboardViewModel = viewModel
            goTranslateBtn.setOnClickListener{
                val action = DashboardFragmentDirections.actionDashboardFragmentToTranslateFragment(viewModel.country)
                findNavController().navigate(action)
            }
            quizBtn.setOnClickListener{
                val action = DashboardFragmentDirections.actionDashboardFragmentToQuizFragment(viewModel.country)
                findNavController().navigate(action)
            }
            mapBtn.setOnClickListener{
                val action = DashboardFragmentDirections.actionDashboardFragmentToMapsFragment(viewModel.country)
                findNavController().navigate(action)
            }
        }

        return binding.root

    }
}