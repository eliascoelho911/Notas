package br.com.eliascoelho911.notas.ui.marcadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.eliascoelho911.notas.R
import br.com.eliascoelho911.notas.model.NotaCompleta
import br.com.eliascoelho911.notas.ui.recyclerview.adapter.MarcadoresAdapter
import kotlinx.android.synthetic.main.fragment_marcadores.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MarcadoresFragment : Fragment() {
    private val viewModel: MarcadoresViewModel by viewModel()
    private val args: MarcadoresFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_marcadores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.todos {
            it.observe(viewLifecycleOwner) { marcadores ->
                fragment_marcadores_lista.adapter = MarcadoresAdapter(marcadores) { marcador ->
                    val notaCompleta = NotaCompleta(args.nota.nota, marcador)
                    val actionNavMarcadoresToNavFormulario =
                        MarcadoresFragmentDirections.navMarcadoresParaNavFormulario(notaCompleta)
                    findNavController().navigate(actionNavMarcadoresToNavFormulario)
                }
            }
        }
    }
}