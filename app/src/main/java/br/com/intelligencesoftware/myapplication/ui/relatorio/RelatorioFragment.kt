package br.com.intelligencesoftware.myapplication.ui.relatorio

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.intelligencesoftware.myapplication.databinding.FragmentRelatorioBinding
import br.com.intelligencesoftware.myapplication.databinding.FragmentStatusBinding
import br.com.intelligencesoftware.myapplication.relatorio.RelatorioAdapter
import br.com.intelligencesoftware.myapplication.status.StatusAdapter
import br.com.intelligencesoftware.myapplication.ui.status.StatusViewModel

class RelatorioFragment : Fragment() {

    private var _binding: FragmentRelatorioBinding? = null
    private val binding get() = _binding!!

    private val relatorioViewModel: RelatorioViewModel by viewModels()

    private var pagina = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRelatorioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        binding.relatorioRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RelatorioAdapter(requireContext())
        binding.relatorioRecyclerView.adapter = adapter

        // Observar LiveData
        relatorioViewModel.relatorioList.observe(viewLifecycleOwner, Observer { relatorioList ->
            adapter.submitList(relatorioList)
        })

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            relatorioViewModel.fetchRelatorio(token, pagina)
        } else {
            // Handle missing token scenario
        }

        binding.btnPrevious.setOnClickListener {
            if (pagina > 1) {  // Certifique-se de que a página não seja menor que 1
                pagina--
                fetchRelatorio(token, pagina)
            }
        }

        binding.btnNext.setOnClickListener {
            pagina++
            fetchRelatorio(token, pagina)
        }

    }

    private fun fetchRelatorio(token: String?, pagina: Int) {
        token?.let {
            relatorioViewModel.fetchRelatorio(it, pagina)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}