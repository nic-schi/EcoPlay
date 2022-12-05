package com.jadehs.ma.ecoplay.zaehneputzen;

import androidx.navigation.Navigation;

import com.jadehs.ma.ecoplay.R;

public class ZahnSchritt1 extends ZahnSchrittFragment {

    public ZahnSchritt1() {
        super(R.string.zaehne_schritt1_header, R.layout.fragment_zahn_schritt1);
    }

    @Override
    public void onWeiter() {
        Navigation.findNavController(this.requireActivity(), R.id.container).navigate(R.id.action_zahnSchritt1_to_zahnSuccessFragment);
    }

}
