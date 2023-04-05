package com.revolshen.firebaseseriesmini.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.api.Distribution
import com.google.firebase.auth.FirebaseAuth
import com.revolshen.firebaseseriesmini.BaseFragment
import com.revolshen.firebaseseriesmini.R
import com.revolshen.firebaseseriesmini.data.Car
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), OnCarItemLongClick {

    private val auth = FirebaseAuth.getInstance()
    private val homeVm by viewModels<HomeViewModel>()
    private val adapter = CarAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout_action -> {
                auth.signOut()
                requireActivity().finish()
            }
        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_home.layoutManager = LinearLayoutManager(requireContext())
        recyclerView_home.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeVm.cars.observe(viewLifecycleOwner, {list ->
            adapter.setCars(list)
        })
    }

    override fun onCarLongClick(car: Car, position: Int) {
        homeVm.addFavCar(car)
    }

}