package com.example.projetonovoecopla.ui.Login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetonovoecopla.data.models.JwtResponse
import com.example.projetonovoecopla.data.models.LoginRequest
import com.example.projetonovoecopla.data.network.RetrofitClient
import com.example.projetonovoecopla.databinding.FragmentTelaLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private var _binding: FragmentTelaLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTelaLoginBinding.inflate(inflater, container, false)

        setupListeners()

        return binding.root
    }

    private fun setupListeners() {
        // Listener para botão Registrar (só um exemplo)
        binding.btnRegistrarTopo.setOnClickListener {
            // Aqui você pode navegar para tela de cadastro, por exemplo
            Toast.makeText(requireContext(), "Registrar clicado", Toast.LENGTH_SHORT).show()
        }

        // Listener para botão Login - este deve fazer a requisição de login
        binding.btnLoginEnviar.setOnClickListener {
            val email = binding.etLoginEmail.text.toString().trim()
            val senha = binding.etSenha.text.toString().trim()

            if (email.isBlank() || senha.isBlank()) {
                Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = LoginRequest(email, senha)
            val authService = RetrofitClient.createAuthService(requireContext())

            authService.login(request).enqueue(object : Callback<JwtResponse> {
                override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        if (token != null) {
                            saveToken(token)
                            Toast.makeText(requireContext(), "Login OK!", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(com.example.projetonovoecopla.R.id.navigation_home)
                        } else {
                            Toast.makeText(requireContext(), "Token inválido", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Erro ao fazer login: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Erro na requisição: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun saveToken(token: String) {
        val prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit().putString("jwt_token", token).apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
