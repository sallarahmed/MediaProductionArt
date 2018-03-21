package com.mediaproductionart.mediaproductionart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PortfolioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PortfolioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PortfolioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context context;
    private View mView;
    private Button btnViewMore_1,btnViewMore_2,btnViewMore_3,btnViewMore_4,btnViewMore_5,btnViewMore_6,
            btnViewMore_7,btnViewMore_8;

    private TextView tvWeb1,tvWeb2,tvWeb3,tvWeb4;

    private ImageView ivWeb1,ivWeb2,ivWeb3,ivWeb4,ivLogo1,ivLogo2,ivLogo3,ivLogo4,ivB_card_1,ivB_card_2,
            ivB_card_3,ivB_card_4,ivP_card_1,ivP_card_2,ivP_card_3,ivP_card_4,ivBrouchure_1,ivBrouchure_2,
            ivBrouchure_3,ivFlyer_1,ivFlyer_2,ivFlyer_3,ivFlyer_4,iv3d_arch_1,iv3d_arch_2,iv3d_arch_3,iv3d_arch_4;

    private GifImageView ivA_logo1,ivA_logo2,ivA_logo3,ivA_logo4;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PortfolioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PortfolioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PortfolioFragment newInstance(String param1, String param2) {
        PortfolioFragment fragment = new PortfolioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_portfolio, container, false);
        context = mView.getContext();
        btnViewMore_1 = mView.findViewById(R.id.btn_view_more_portfolio_1);
        btnViewMore_1.setOnClickListener((View.OnClickListener) context);

        btnViewMore_2 = mView.findViewById(R.id.btn_view_more_portfolio_2);
        btnViewMore_2.setOnClickListener((View.OnClickListener) context);

        btnViewMore_3 = mView.findViewById(R.id.btn_view_more_portfolio_3);
        btnViewMore_3.setOnClickListener((View.OnClickListener) context);

        btnViewMore_4 = mView.findViewById(R.id.btn_view_more_portfolio_4);
        btnViewMore_4.setOnClickListener((View.OnClickListener) context);

        btnViewMore_5 = mView.findViewById(R.id.btn_view_more_portfolio_5);
        btnViewMore_5.setOnClickListener((View.OnClickListener) context);

        btnViewMore_6 = mView.findViewById(R.id.btn_view_more_portfolio_6);
        btnViewMore_6.setOnClickListener((View.OnClickListener) context);

        btnViewMore_7 = mView.findViewById(R.id.btn_view_more_portfolio_7);
        btnViewMore_7.setOnClickListener((View.OnClickListener) context);

        btnViewMore_8 = mView.findViewById(R.id.btn_view_more_portfolio_8);
        btnViewMore_8.setOnClickListener((View.OnClickListener) context);


        ivWeb1 = mView.findViewById(R.id.web_portfolio_1);
        ivWeb1.setOnClickListener((View.OnClickListener) context);
        tvWeb1 = mView.findViewById(R.id.web_portfolio_text_1);
        tvWeb1.setOnClickListener((View.OnClickListener) context);

        ivWeb2 = mView.findViewById(R.id.web_portfolio_2);
        ivWeb2.setOnClickListener((View.OnClickListener) context);
        tvWeb2 = mView.findViewById(R.id.web_portfolio_text_2);
        tvWeb2.setOnClickListener((View.OnClickListener) context);

        ivWeb3 = mView.findViewById(R.id.web_portfolio_3);
        ivWeb3.setOnClickListener((View.OnClickListener) context);
        tvWeb3 = mView.findViewById(R.id.web_portfolio_text_3);
        tvWeb3.setOnClickListener((View.OnClickListener) context);

        ivWeb4 = mView.findViewById(R.id.web_portfolio_4);
        ivWeb4.setOnClickListener((View.OnClickListener) context);
        tvWeb4 = mView.findViewById(R.id.web_portfolio_text_4);
        tvWeb4.setOnClickListener((View.OnClickListener) context);


        ivLogo1 = mView.findViewById(R.id.logo_portfolio_1);
        ivLogo1.setOnClickListener((View.OnClickListener) context);

        ivLogo2 = mView.findViewById(R.id.logo_portfolio_2);
        ivLogo2.setOnClickListener((View.OnClickListener) context);

        ivLogo3 = mView.findViewById(R.id.logo_portfolio_3);
        ivLogo3.setOnClickListener((View.OnClickListener) context);

        ivLogo4 = mView.findViewById(R.id.logo_portfolio_4);
        ivLogo4.setOnClickListener((View.OnClickListener) context);


        ivA_logo1 = mView.findViewById(R.id.a_logo_portfolio_1);
        ivA_logo1.setOnClickListener((View.OnClickListener) context);

        ivA_logo2 = mView.findViewById(R.id.a_logo_portfolio_2);
        ivA_logo2.setOnClickListener((View.OnClickListener) context);

        ivA_logo3 = mView.findViewById(R.id.a_logo_portfolio_3);
        ivA_logo3.setOnClickListener((View.OnClickListener) context);

        ivA_logo4 = mView.findViewById(R.id.a_logo_portfolio_4);
        ivA_logo4.setOnClickListener((View.OnClickListener) context);


        ivB_card_1 = mView.findViewById(R.id.b_card_portfolio_1);
        ivB_card_1.setOnClickListener((View.OnClickListener) context);

        ivB_card_2 = mView.findViewById(R.id.b_card_portfolio_2);
        ivB_card_2.setOnClickListener((View.OnClickListener) context);

        ivB_card_3 = mView.findViewById(R.id.b_card_portfolio_3);
        ivB_card_3.setOnClickListener((View.OnClickListener) context);

        ivB_card_4 = mView.findViewById(R.id.b_card_portfolio_4);
        ivB_card_4.setOnClickListener((View.OnClickListener) context);


        ivP_card_1 = mView.findViewById(R.id.p_card_portfolio_1);
        ivP_card_1.setOnClickListener((View.OnClickListener) context);

        ivP_card_2 = mView.findViewById(R.id.p_card_portfolio_2);
        ivP_card_2.setOnClickListener((View.OnClickListener) context);

        ivP_card_3 = mView.findViewById(R.id.p_card_portfolio_3);
        ivP_card_3.setOnClickListener((View.OnClickListener) context);

        ivP_card_4 = mView.findViewById(R.id.p_card_portfolio_4);
        ivP_card_4.setOnClickListener((View.OnClickListener) context);


        ivBrouchure_1 = mView.findViewById(R.id.brouchers_portfolio_1);
        ivBrouchure_1.setOnClickListener((View.OnClickListener) context);

        ivBrouchure_2 = mView.findViewById(R.id.brouchers_portfolio_2);
        ivBrouchure_2.setOnClickListener((View.OnClickListener) context);

        ivBrouchure_3 = mView.findViewById(R.id.brouchers_portfolio_3);
        ivBrouchure_3.setOnClickListener((View.OnClickListener) context);

        ivFlyer_1 = mView.findViewById(R.id.flyer_portfolio_1);
        ivFlyer_1.setOnClickListener((View.OnClickListener) context);

        ivFlyer_2 = mView.findViewById(R.id.flyer_portfolio_2);
        ivFlyer_2.setOnClickListener((View.OnClickListener) context);

        ivFlyer_3 = mView.findViewById(R.id.flyer_portfolio_3);
        ivFlyer_3.setOnClickListener((View.OnClickListener) context);

        ivFlyer_4 = mView.findViewById(R.id.flyer_portfolio_4);
        ivFlyer_4.setOnClickListener((View.OnClickListener) context);


        iv3d_arch_1 = mView.findViewById(R.id.portfolio_3d_archicture_1);
        iv3d_arch_1.setOnClickListener((View.OnClickListener) context);

        iv3d_arch_2 = mView.findViewById(R.id.portfolio_3d_archicture_2);
        iv3d_arch_2.setOnClickListener((View.OnClickListener) context);

        iv3d_arch_3 = mView.findViewById(R.id.portfolio_3d_archicture_3);
        iv3d_arch_3.setOnClickListener((View.OnClickListener) context);

        iv3d_arch_4 = mView.findViewById(R.id.portfolio_3d_archicture_4);
        iv3d_arch_4.setOnClickListener((View.OnClickListener) context);


        return mView;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
