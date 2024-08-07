package br.com.intelligencesoftware.myapplication.ui.camera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.intelligencesoftware.myapplication.CadastrarCameraActivity
import br.com.intelligencesoftware.myapplication.camera.CameraAdapter
import br.com.intelligencesoftware.myapplication.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private val cameraViewModel: CameraViewModel by viewModels()

    private var pagina = 1
    private var pesquisa = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar RecyclerView
        binding.cameraRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CameraAdapter(requireContext())
        binding.cameraRecyclerView.adapter = adapter

        // Observar LiveData
        cameraViewModel.cameraList.observe(viewLifecycleOwner, Observer { cameraList ->
            adapter.submitList(cameraList)
        })

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)

        if (token != null) {
            cameraViewModel.fetchCamera(token, pesquisa, pagina)
        } else {
            // Handle missing token scenario
        }

        binding.btnPrevious.setOnClickListener {
            if (pagina > 1) {  // Certifique-se de que a página não seja menor que 1
                pagina--
                fetchCamera(token, pesquisa, pagina)
            }
        }

        binding.btnNext.setOnClickListener {
            pagina++
            fetchCamera(token, pesquisa, pagina)
        }

        // Configurar botão de pesquisa
        binding.searchButton.setOnClickListener {
            val newPesquisa = binding.searchEditText.text.toString()
            if (newPesquisa.isNotEmpty()) {
                pesquisa = newPesquisa
                pagina = 1  // Reiniciar para a primeira página quando uma nova pesquisa é feita
                fetchCamera(token, pesquisa, pagina)
            }
        }

        binding.btnCadastrar.setOnClickListener {
            val intent = Intent(requireContext(), CadastrarCameraActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchCamera(token: String?, pesquisa: String, pagina: Int) {
        token?.let {
            cameraViewModel.fetchCamera(it, pesquisa, pagina)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}