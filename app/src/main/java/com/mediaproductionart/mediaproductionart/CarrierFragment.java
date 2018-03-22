package com.mediaproductionart.mediaproductionart;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarrierFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarrierFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarrierFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private View mView;
    private EditText etName, etEmail, etDOB, etContNo, etCountry, etCity;
    private Spinner sAppliedFor, sQualification;
    private Button btnUpload,btnSubmit;
    int requestCode = 1;
    private String [] applied = {"Select Applying for","Internship","Job"};
    private String [] qualification = {"Select your Qualification","Matriculation","Intermediate","BSC","B.Com","BCS"
    ,"MBA","BBA","BA","BSIT","MSIT","MA","Software Engineering","Other"};

    private OnFragmentInteractionListener mListener;

    public CarrierFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarrierFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarrierFragment newInstance(String param1, String param2) {
        CarrierFragment fragment = new CarrierFragment();
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
        mView = inflater.inflate(R.layout.fragment_carrier, container, false);
        context = mView.getContext();
        etName = mView.findViewById(R.id.etNameCarrier);
        etEmail = mView.findViewById(R.id.etEmailCarrier);
        etDOB = mView.findViewById(R.id.etDOB_Carrier);
        etContNo = mView.findViewById(R.id.etContNoCarrier);
        etCountry = mView.findViewById(R.id.etCountryCarrier);
        etCity = mView.findViewById(R.id.etCityCarrier);
        sAppliedFor = mView.findViewById(R.id.spinnerApplierFor);
        sQualification = mView.findViewById(R.id.spinnerQualification);
        btnUpload = mView.findViewById(R.id.btnUploadCV_Carrier);
        btnSubmit = mView.findViewById(R.id.btnSubmitCarrier);

        ArrayAdapter<String> qAdp= new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,qualification);
        qAdp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sQualification.setAdapter(qAdp);

        ArrayAdapter<String> aAdp= new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,applied);
        aAdp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sAppliedFor.setAdapter(aAdp);

        btnUpload.setOnClickListener((View.OnClickListener) context);
        btnSubmit.setOnClickListener((View.OnClickListener) context);


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
