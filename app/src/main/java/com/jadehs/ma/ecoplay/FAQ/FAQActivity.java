package com.jadehs.ma.ecoplay.FAQ;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.jadehs.ma.ecoplay.EcoPlayActivity;
import com.jadehs.ma.ecoplay.R;
import com.jadehs.ma.ecoplay.utils.Animator;

import java.util.List;

public class FAQActivity extends EcoPlayActivity {

    public FAQActivity() {
        super(true, R.string.actionbar_menu_faq, R.drawable.question_round, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

//        FragmentManager fragM = this.getSupportFragmentManager();
//        FragmentTransaction trans = fragM.beginTransaction();

//        int i = 0;
//        FAQ[] faqs = new FAQManager(this).read(FAQManager.FILENAME);
//        for (FAQ faq : faqs) {
//            FAQFragment frag = FAQFragment.newInstance(faq);
//            frag.markAsCollapsed(i == 0);
//            trans.add(R.id.alle_faqs, frag);
//            i++;
//        }
//        trans.commit();
    }

    public void closeOtherFAQs(Fragment current) {
        List<Fragment> fragments = this.getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof FAQFragment && !current.equals(fragment)) {
                View view = fragment.requireView();
                Animator.animateCollapse(view, view.findViewById(R.id.faqAntwort), view.findViewById(R.id.faqIcon), true);
            }
        }
    }

}