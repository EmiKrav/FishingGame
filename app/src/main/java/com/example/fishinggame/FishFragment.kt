package com.example.fishinggame

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentFishBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random


class FishFragment : Fragment() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding = FragmentFishBinding.inflate(inflater)


        val viewModel: DataStoreViewModel by viewModels()
        var sk: Float = 0.0F;
        var kiekis: Int = 0
        var Laikas: String =""
        viewModel.getPinigai.observe(viewLifecycleOwner) {
            sk = it;
        }

        viewModel.getKiekis.observe(viewLifecycleOwner) {
            kiekis = it;
        }
        viewModel.getLaikai.observe(viewLifecycleOwner) {
            Laikas = it;
        }
        var laikas =  LocalDateTime.now().
        format(DateTimeFormatter.ofPattern("yyyy:MM:dd - HH:mm:ss")).toString() + System.lineSeparator();

        val list = listOf("Alligator Gar ",
            "Amberjack ",
            "Arapaima ",
            "Arctic Char ",
            "Asp ",
            "Barracuda (Great) ",
            "Barracuda (Pacific) ",
            "Barramundi ",
            "Bass (Australian) ",
            "Bass (Largemouth) ",
            "Bass (Peacock) ",
            "Bass (Rainbow) ",
            "Bass (Smallmouth) ",
            "Bass (Spotted) ",
            "Bass (Striped) ",
            "Bass (White) ",
            "Black Drum ",
            "Black Jewfish ",
            "Black Rockfish ",
            "Bluefish ",
            "Bluenose Warehou ",
            "Bohar Snapper ",
            "Bonefish ",
            "Bonito ",
            "Bowfin ",
            "Bream (Black) ",
            "Bream (Common) ",
            "Bream (Fingermark) ",
            "Brook Trout ",
            "Brown Trout ",
            "Bullhead ",
            "Burbot ",
            "Calico Bass ",
            "California Corbina ",
            "California Sheephead ",
            "Carp ",
            "Catfish ",
            "Cero Mackerel ",
            "Clam ",
            "Clown Knife Fish ",
            "Coalfish ",
            "Cobia ",
            "Cod ",
            "Cod (Blue) ",
            "Cod (Breaksea) ",
            "Common Ling ",
            "Common Pandora ",
            "Conger Eel ",
            "Coral Trout ",
            "Crab ",
            "Crappie ",
            "Crayfish ",
            "Cutthroat Trout ",
            "Dentex ",
            "Dhufish ",
            "Dogfish ",
            "Dolly Varden ",
            "Dolphin (Mahi Mahi) ",
            "Flathead ",
            "Flounder ",
            "Freshwater Drum ",
            "Garfish ",
            "Geelbek (Cape Salmon) ",
            "Gilt-head (Seabream) ",
            "Golden Dorado ",
            "Grayling ",
            "Grouper (Black) ",
            "Grouper (Broomtail) ",
            "Grouper (Dusky) ",
            "Grouper (Gag) ",
            "Grouper (Goliath) ",
            "Grouper (Hamour) ",
            "Grouper (Red) ",
            "Grouper (Scamp) ",
            "Grouper (Snowy) ",
            "Grunt ",
            "Grunter (Javelin) ",
            "Gurnard ",
            "Haddock ",
            "Halibut ",
            "Hapuka ",
            "Herring ",
            "Hogfish ",
            "Hybrid Striped Bass ",
            "Indian Threadfish ",
            "Jack (African Pompano) ",
            "Jack (Almaco) ",
            "Jack (Horse-eye) ",
            "Jack Crevalle ",
            "Jobfish (Green) ",
            "Jobfish (Rusty) ",
            "John Dory ",
            "Kahawai ",
            "King George Whiting ",
            "King Mackerel (Kingfish) ",
            "Kob ",
            "Ladyfish ",
            "Lake Trout ",
            "Leatherjacket ",
            "Leerfish (Garrick) ",
            "Lingcod ",
            "Lionfish ",
            "Little Tunny (False Albacore) ",
            "Lobster ",
            "Long-Tail Red Snapper (Onaga) ",
            "Mackerel (Atlantic) ",
            "Marlin (Black) ",
            "Marlin (Blue) ",
            "Marlin (Striped) ",
            "Marlin (White) ",
            "Milkfish ",
            "Morwong ",
            "Murray Cod ",
            "Muskellunge (Musky) ",
            "Musselcracker ",
            "Nannygai ",
            "Needlefish ",
            "Nursehound (Bull Huss) ",
            "Paddlefish ",
            "Papuan Black Snapper ",
            "Payara ",
            "Pearl Perch ",
            "Pejerrey (Silverside) ",
            "Perch (European) ",
            "Perch (Golden) ",
            "Perch (Silver) ",
            "Perch (White) ",
            "Perch (Yellow) ",
            "Permit ",
            "Pike (Northern) ",
            "Pike Perch (Zander) ",
            "Pink Maomao ",
            "Pollock ",
            "Pompano ",
            "Queen Snapper (Australian) ",
            "Queen Snapper (Caribbean) ",
            "Queenfish ",
            "Queensland Groper (Giant Grouper) ",
            "Rainbow Runner ",
            "Rainbow Trout (Steelhead) ",
            "Ray ",
            "Redfish ",
            "Rockfish ",
            "Roosterfish ",
            "Sailfish ",
            "Salmon ",
            "Salmon (Atlantic) ",
            "Salmon (Australian) ",
            "Salmon (Chinook) ",
            "Salmon (Chum) ",
            "Salmon (Coho) ",
            "Salmon (Pink) ",
            "Salmon (Sockeye) ",
            "Samson Fish ",
            "Sand Bass ",
            "Saratoga ",
            "Sauger ",
            "Scallop ",
            "Scorpionfish ",
            "Sculpin ",
            "Scup (Porgy) ",
            "Seabass (Black) ",
            "Seabass (European) ",
            "Seabass (Giant) ",
            "Seabass (White) ",
            "Shad ",
            "Shark (Blacktip) ",
            "Shark (Blue) ",
            "Shark (Bonnethead) ",
            "Shark (Bull) ",
            "Shark (Greenland) ",
            "Shark (Gummy) ",
            "Shark (Hammerhead) ",
            "Shark (Lemon) ",
            "Shark (Leopard) ",
            "Shark (Mako) ",
            "Shark (Nurse) ",
            "Shark (Porbeagle) ",
            "Shark (Thresher) ",
            "Shark (Tiger) ",
            "Shark (Tope) ",
            "Sheepshead ",
            "Short-Tail Red Snapper (Ehu) ",
            "Shrimp ",
            "Sierra Mackerel ",
            "Skate ",
            "Snakehead ",
            "Snapper (Cubera) ",
            "Snapper (Lane) ",
            "Snapper (Mangrove) ",
            "Snapper (Mullet) ",
            "Snapper (Mutton) ",
            "Snapper (Pink) ",
            "Snapper (Red Emperor) ",
            "Snapper (Red) ",
            "Snapper (Vermilion) ",
            "Snapper (Yellowtail) ",
            "Snoek ",
            "Snook ",
            "Spadefish ",
            "Spangled Emperor ",
            "Spanish Mackerel ",
            "Spanish Mackerel (Narrow-barred) ",
            "Spearfish (Longbill) ",
            "Spearfish (Shortbill) ",
            "Speckled Trout ",
            "Spotted Seatrout ",
            "Squid ",
            "Sturgeon ",
            "Sunfish ",
            "Sweetlip Emperor ",
            "Swordfish ",
            "Tailor ",
            "Tarakihi ",
            "Tarpon ",
            "Tautog ",
            "Threadfin Salmon ",
            "Tigerfish ",
            "Tigerfish (Goliath) ",
            "Tilapia ",
            "Tilefish ",
            "Trevally (Bigeye) ",
            "Trevally (Blacktip) ",
            "Trevally (Bluefin) ",
            "Trevally (Giant) ",
            "Trevally (Golden) ",
            "Trevally (Silver) ",
            "Triggerfish (Gray) ",
            "Tripletail ",
            "Trumpeter (Striped) ",
            "Tuna (Albacore) ",
            "Tuna (Bigeye) ",
            "Tuna (Blackfin) ",
            "Tuna (Bluefin) ",
            "Tuna (Dogtooth) ",
            "Tuna (Longtail) ",
            "Tuna (Skipjack) ",
            "Tuna (Yellowfin) ",
            "Wahoo ",
            "Walleye ",
            "Weakfish ",
            "Whitefish (Lake) ",
            "Whiting ",
            "Wolffish ",
            "Wrasse (Bluethroat) ",
            "Wrasse (Humphead) ",
            "Yellowfish ",
            "Yellowtail Amberjack ")
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]


       var dd : Float = Random.nextFloat() * (100F - 0.1F) + 0.01F
        dd = String.format("%.2f", dd).toFloat()

        var kk : Float = Random.nextFloat() * (100F - 0.010F) + 0.010F
        kk = String.format("%.3f", kk).toFloat()

        binding.textView5.text = randomElement;
        binding.textView3.text = "$kk kg";
        binding.textView4.text = "$dd €";

      //  viewModel.savePinigai(0F);
        //  viewModel.saveKiekis(0);
          // viewModel.saveLaikai("");
      //  viewModel.saveEzerai("");
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.savePinigai(sk + dd);
            viewModel.saveKiekis(kiekis + 1);
            viewModel.saveLaikai(Laikas +  System.lineSeparator()+ LocalDateTime.now().
            format(DateTimeFormatter.ofPattern("yyyy:MM:dd - HH:mm:ss")).toString());

            val action =
                FishFragmentDirections.actionFishFragmentToGameScreenFragment()
            findNavController().navigate(action)
        }
        binding.button3
            .setOnClickListener {
                viewModel.savePinigai(sk + dd);
                viewModel.saveKiekis(kiekis + 1);
                viewModel.saveLaikai(Laikas +  System.lineSeparator()+ LocalDateTime.now().
                format(DateTimeFormatter.ofPattern("yyyy:MM:dd - HH:mm:ss")).toString());

                val action =
                    FishFragmentDirections.actionFishFragmentToGameScreenFragment()
                findNavController().navigate(action)
            }

        return binding.root
    }


}
