package az.khayalsharifli.rickandmorty.ui.content.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import az.khayalsharifli.rickandmorty.base.BaseFragment
import az.khayalsharifli.rickandmorty.databinding.FragmentDetailBinding
import coil.load
import kotlin.reflect.KClass

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate
    override val kClass: KClass<DetailViewModel>
        get() = DetailViewModel::class

    private val args: DetailFragmentArgs by navArgs()

    override val bindViews: FragmentDetailBinding.() -> Unit = {
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        tvIdCharacter.text = args.result.id.toString()
        tvName.text = args.result.name
        tvStatus.text = args.result.status
        tvNEpisodes.text = args.result.episode.size.toString()
        tvGender.text = args.result.gender
        tvSpecie.text = args.result.species
        tvLocation.text = args.result.location.name
        tvOrigin.text = args.result.origin.name
        imageCharacter.load(args.result.image)
    }

}