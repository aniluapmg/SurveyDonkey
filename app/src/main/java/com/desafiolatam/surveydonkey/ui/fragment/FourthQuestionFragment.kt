package com.desafiolatam.surveydonkey.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.desafiolatam.surveydonkey.databinding.FragmentFourthQuestionBinding
import com.desafiolatam.surveydonkey.viewmodel.MainViewModel

class FourthQuestionFragment : Fragment() {

    private var _binding: FragmentFourthQuestionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }


    //Permisos
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            downloadResults()
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun downloadResults() {
        // Aquí iría el código para descargar los resultados
        Toast.makeText(context, "Descargando resultados...", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.run {
            answer41.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


                override fun afterTextChanged(s: Editable?) {
                    viewModel.setEmail(s.toString())
                }
            })

            answer42.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


                override fun afterTextChanged(s: Editable?) {
                    viewModel.setComment(s.toString())
                }
            })

            submitButton.setOnClickListener {
                if (answer41.text.isNullOrEmpty() || answer42.text.isNullOrEmpty()) {
                    Toast.makeText(context, "Por favor, complete ambos campos", Toast.LENGTH_SHORT).show()
                } else {
                    checkPermissionAndSave()
                }
            }

        }
    }

    private fun checkPermissionAndSave() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                saveResults()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) -> {
                Toast.makeText(context, "Se necesita permiso para guardar los resultados", Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    private fun saveResults() {
        val firstResult = viewModel.getFirstResult()
        val secondResult = viewModel.getSecondResult()
        val thirdResult = viewModel.getThirdResult()
        val emailResult = viewModel.getEmailResult()
        val commentResult = viewModel.getCommentResult()

        // Aquí iría el código para guardar los resultados, por ahora solo mostramos un Toast
        Toast.makeText(context, "Resultados:\n$firstResult\n$secondResult\n$thirdResult\n$emailResult\n$commentResult", Toast.LENGTH_LONG).show()
    }
            override fun onDestroyView() {
                super.onDestroyView()
                _binding = null
            }

        }
