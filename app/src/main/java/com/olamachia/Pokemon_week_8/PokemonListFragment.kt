package com.olamachia.Pokemon_week_8

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.Pokemon_week_8.constant.NetWorkConnection
import com.olamachia.Pokemon_week_8.ViewModel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

class PokemonListFragment : Fragment() {

    private lateinit var viewModel: PokemonViewModel

    var isFragmentVisible = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        /**
         * Observe network connectivity */

        val networkConnection = NetWorkConnection(activity?.applicationContext!!)
        networkConnection.observe(requireActivity(), Observer {
            if (it) {
                if (isFragmentVisible) {
                    Log.i("Api Called", "Result Api Called")
                    pokemon_list.visibility = View.VISIBLE
                    network_error_handling.visibility = View.GONE
                    viewModel.getPokemonItem()
                }

            } else {
                if (isFragmentVisible) {
                    pokemon_list.visibility = View.GONE
                    network_error_handling.visibility = View.VISIBLE
                }
            }
        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var adapter: PokemonAdapter = PokemonAdapter(mutableListOf())

        viewModel.getPokemonItem()

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            adapter.setMyList(it)
        })

        rvPokemonItem.adapter = adapter
        rvPokemonItem.layoutManager = GridLayoutManager(context, 2)

        viewModel.failure.observe(viewLifecycleOwner, Observer {
            noNetworkResponse()
        })

        rvPokemonItem.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                val bundle = Bundle()
                bundle.putString("POKEMON_ID", "${position + 1}")

                val currentPokemonName = adapter.pokemonList[position]?.name

                bundle.putString("POKEMON_NAME", currentPokemonName)

                val displayPokemonFragment = DisplayPokemonFragment()
                displayPokemonFragment.arguments = bundle

                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, displayPokemonFragment)
                    addToBackStack(null)
                    commit()
                }
                //Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object :
            RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClick(holder.adapterPosition, view)
                }
            }

        })
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View)
    }

    fun noNetworkResponse() {
        rvPokemonItem.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
        network_error_handling.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFragmentVisible = false
    }

}