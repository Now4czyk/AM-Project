package com.kn.amproject.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.kn.amproject.BaseFragment
import com.kn.amproject.R
import com.kn.amproject.data.Tool
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), OnToolItemLongClick {

    private val auth = FirebaseAuth.getInstance()
    private val homeVm by viewModels<HomeViewModel>()

    //setting listening by adapter to this fragment
    private val adapter = ToolAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_action -> {
                auth.signOut()
                requireActivity().finish()
            }
        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //connecting context
        recyclerView_home.layoutManager = LinearLayoutManager(requireContext())
        //connecting adapter
        recyclerView_home.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //if we receive tools from firebase then set them to adapter
        homeVm.tools.observe(viewLifecycleOwner, { list ->
            adapter.setTools(list)
        })
    }

    override fun onToolLongClick(tool: Tool, position: Int) {
        homeVm.addFavTool(tool, activity?.applicationContext!!, getString(R.string.addedFavTool))
    }
}