package br.com.intelligencesoftware.myapplication.ui.eventos

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.intelligencesoftware.myapplication.databinding.FragmentEventosBinding
import br.com.intelligencesoftware.myapplication.databinding.FragmentStatusBinding
import br.com.intelligencesoftware.myapplication.eventos.CamEventosAdapter
import br.com.intelligencesoftware.myapplication.status.StatusAdapter
import br.com.intelligencesoftware.myapplication.ui.status.StatusViewModel

class EventosFragment : Fragment() {

    private var _binding: FragmentEventosBinding? = null
    private val binding get() = _binding!!

    private val eventosViewModel: EventosViewModel by viewModels()

    private var pagina = 1
    private var pesquisa = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        binding.eventosRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CamEventosAdapter(requireContext())
        binding.eventosRecyclerView.adapter = adapter

        // Observar LiveData
        eventosViewModel.cameventosList.observe(viewLifecycleOwner, Observer { cameventosList ->
            adapter.submitList(cameventosList)
        })

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            eventosViewModel.fetchCamEventos(token, pesquisa, pagina)
        } else {
            // Handle missing token scenario
        }

        binding.btnPrevious.setOnClickListener {
            if (pagina > 1) {  // Certifique-se de que a página não seja menor que 1
                pagina--
                fetchCamEventos(token, pesquisa, pagina)
            }
        }

        binding.btnNext.setOnClickListener {
            pagina++
            fetchCamEventos(token, pesquisa, pagina)
        }

        // Configurar botão de pesquisa
        binding.searchButton.setOnClickListener {
            val newPesquisa = binding.searchEditText.text.toString()
            if (newPesquisa.isNotEmpty()) {
                pesquisa = newPesquisa
                pagina = 1  // Reiniciar para a primeira página quando uma nova pesquisa é feita
                fetchCamEventos(token, pesquisa, pagina)
            }
        }
    }

    private fun fetchCamEventos(token: String?, pesquisa: String, pagina: Int) {
        token?.let {
            eventosViewModel.fetchCamEventos(it, pesquisa, pagina)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}