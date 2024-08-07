package br.com.intelligencesoftware.myapplication.ui.status

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.intelligencesoftware.myapplication.databinding.FragmentStatusBinding
import br.com.intelligencesoftware.myapplication.status.StatusAdapter

class StatusFragment : Fragment() {

    private var _binding: FragmentStatusBinding? = null
    private val binding get() = _binding!!

    private val statusViewModel: StatusViewModel by viewModels()

    private var pagina = 1
    private var pesquisa = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = StatusAdapter(requireContext())
        binding.recyclerView.adapter = adapter

        // Observar LiveData
        statusViewModel.statusList.observe(viewLifecycleOwner, Observer { statusList ->
            adapter.submitList(statusList)
        })

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            statusViewModel.fetchStatus(token, pesquisa, pagina)
        } else {
            // Handle missing token scenario
        }

        binding.btnPrevious.setOnClickListener {
            if (pagina > 1) {  // Certifique-se de que a página não seja menor que 1
                pagina--
                fetchStatus(token, pesquisa, pagina)
            }
        }

        binding.btnNext.setOnClickListener {
            pagina++
            fetchStatus(token, pesquisa, pagina)
        }

        binding.searchButton.setOnClickListener {
            val newPesquisa = binding.searchEditText.text.toString()
            if (newPesquisa.isNotEmpty()) {
                pesquisa = newPesquisa
                pagina = 1  // Reiniciar para a primeira página quando uma nova pesquisa é feita
                fetchStatus(token, pesquisa, pagina)
            }
        }
    }

    private fun fetchStatus(token: String?, pesquisa: String, pagina: Int) {
        token?.let {
            statusViewModel.fetchStatus(it, pesquisa, pagina)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
