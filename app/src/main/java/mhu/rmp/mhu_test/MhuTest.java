package mhu.rmp.mhu_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import mhu.rmp.R;
import mhu.rmp.TestAdviced.TestAdvised;
import mhu.rmp.activity.MainActivity;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.mhu_test.model.MHU_Test;
import mhu.rmp.pref_manager.PrefManager;
import mhu.rmp.user_login.LoginActivity;
import mhu.rmp.utils.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static mhu.rmp.mhu_test.apihelper.Web_MhuApi_Helper.webAddTestAttributeValues;

public class MhuTest extends Fragment {

    JSONArray jsonoarray=new JSONArray();



    private Toolbar mhu_toolbar;
    private LinearLayout completeBloodCountHomogramLinearlayout,hbLinearlayout,tlcLinearlayout,dlcLinearlayout,
            pcvLinearlayout,rbcLinearlayout,plateletCountLinearlayout,mcvMchMchcLinearlayout,esrLinearlayout,
            aecLinearlayout,dbilLinearlayout,idbilLinearlayout,tbilLinearlayout,tprotLinearlayout,
            agRatLinearlayout,altLinearlayout,astLinearlayout,alpLinearlayout,compLftLinearlayout,ggtLinearlayout,
            albLinearlayout,ureaLinearlayout,creatLinearlayout,uricAcidLinearlayout,electrolyteLinearlayout,liverFunctionTprotLinearlayout,
            a1bKidneyFunctionLinearlayout,a1bCrRatioLinearlayout,microAlbuminLinearlayout,kidneyCompKftLinearlayout,
            cholesterolLinearlayout,tagLinearlayout,hdlLinearlayout,completeLipidProfileLinearlayout,fbgLinearlayout,twohrLinearlayout,
            randomLinearlayout,glucoseProfileGttLinearlayout,hba1cLinearlayout,urinaryMicroAlbuminLinearlayout,insulinFppLinearlayout,
            widaltextLinearlayout,typhidotTextLinearlayout,malariaSerologyTextLinearlayout,raFactorTextLinearlayout,hbsagTextLinearlayout,routineMicrosocopicLinearlayout,
            proteinLinearlayout,sugarLinearlayout,ketoneBodiesLinearlayout,bileSaltBilePigmentsLinearlayout,urinePregnancyTestLinearlayout,crpTextLinearlayout,antiHcvLinearlayout,
            hivTextLinearlayout,vdrlTextLinearlayout,gctTextLinearlayout,aboRhTextLinearlayout;

    private TextInputEditText edtCbbHgmReferenceValue,edtCbbHgmReading,edtHbReferenceValue,edtHbReading,
            edtTlcReferenceValue,edtTlcReading,edtDlcReferenceValue,edtDlcReading,edtPcvReferenceValue,
            edtPcvReading,edtRbcReferenceValue,edtRbcReading,edtPlateletCountReferencevalue,edtPlateletCountReading,
            edtMcvMchMchcReferenceValue,edtMcvMchMchcReading,edtEsrReferenceValue,edtEsrReading,edtAecReferenceValue,edtAecReading,
            edtDBilReferenceValue,edtDBilReading,edtIdBilReferenceValue,edtIdBilReading,edtTBilReferenceValue,edtTBilReading,edtTProtReferenceValue,
            edtTProtReading,edtAgRatReferenceValue,edtAgRatReading,edtAltReferenceValue,edtAltReading,edtAstReferenceValue,edtAstReading,edtAlpReferenceValue,
            edtAlpReading,edtCompLftReferenceValue,edtCompLftReading,edtGgtReferenceValue,edtGggtReading,edtAlbReferenceValue,edtAlbReading,
            edtUreaReferenceValue,edtUreaReading,edtCreatReferenceValue,edtCreatReading,edtUricAcidReferenceValue,edtUricAcidReading,edtElectrolyteReferenceValue,
            edtElectrolyteReading,edtLiverfunctionTprotReferenceValue,edtLiverfunctionTprotReading,edtA1bKidneyFunctionReferenceValue,edtA1bKidneyFunctionReading,edtA1bCrRatioReferenceValue,
            edtA1bCrRatioReading,edtMicroAlbuminReferenceValue,edtMicroAlbuminReading,edtKidneyCompKftReferenceValue,edtKidneyCompKftReading,
            edtCholesterolReferenceValue,edtCholesterolReading,edtTagReferenceValue,edtTagReading,edtHdlReferenceValue,edtHdlReading,
            edtCompleteLipidProfileReferenceValue,edtCompleteLipidProfileReading,edtFbgReferenceValue,edtFbgReading,edtTwohrReferenceValue,edtTwohrReading,
            edtRandomReferenceValue,edtRandomReading,edtGlucoseProfileGttReferenceValue,edtGlucoseProfileGttReading,edtHba1cReferenceValue,edtHba1cReading,
            edtUrinaryMicroAlbuminRefrancevalue,edtUrinaryMicroAlbuminReading,edtInsulinFppRefrancevalue,edtInsulinFppReading,
            edtWidaltextReferenceValue,edtWidaltextReading,edtTyphidotTextReferenceValue,edtTyphidotTextReading,edtMalariaSerologyTextReferenceValue,edtMalariaSerologyTextReading,
            edtRaFactorTextReferenceValue,edtRaFactorTextReading,edtHbsagTextReferenceValue,edtHbsagTextReading,edtRoutineMicrosocopicReferenceValue,edtRoutineMicrosocopicReading,
            edtProteinReferenceValue,edtProteinReading,edtSugarReferenceValue,edtSugarReading,edtKetoneBodiesReferenceValue,edtKetoneBodiesReading,edtBileSaltBilePigmentsReferenceValue,
            edtBileSaltBilePigmentsReading,edtUrinePregnancyTestReferenceValue,edtUrinePregnancyTestReading,edtCrpTextReferenceValue,edtCrpTextReading,edtAntiHcvTextReferenceValue,edtAntiHcvTextReading,
            edtHivTextReferenceValue,edtHivTextReading,edtVdrlTextReferenceValue,edtVdrlTextReading,edtGctTextReferenceValue,edtGctTextReading,edtAboRhTextReferenceValue,edtAboRhTextReading;

    private CheckBox checkboxCompleteBloodCountHomogram,checkboxLiverFunctionTest,checkboxKidneyFunctionTest,
            checkboxLipidProfile,checkboxGlucoseProfile,checkboxWidal,checkboxTyphidot,checkboxMalariaSerology,
            checkboxRaFactor,checkboxHbsag,checkboxCrp,checkboxAntiHcv,checkboxHiv,checkboxVdrl,checkboxGct,checkboxAboRh,checkboxUrineExamination;

    private String selected_blood_count_homogram = "",liver_function_test="",kidney_function_test="",
    liquid_profile="",glucose_profile="",widal="",typhidot="",malaria_serology="",ra_factor="",hbsag="",
    crp="",antihcv="",hiv="",vdrl="",gct="",abo_rh="",urine_examination="";


    private Button btnSaveMhu;
    private MHU_Test mhuTest;

    private PrefManager prefManager;


    public MhuTest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_mhu_test, container, false);

        initializations(rootview);

        saveClickListner();

        setSelectedBloodCount();
        liverFunctionTest();
        kidneyFunctionTest();
        lipidProfile();
        glucoseProfile();
        widal();
        typhidot();
        malariaSerology();
        raFactor();
        hbsag();
        urineExamination();
        crp();
        antiHcv();
        hiv();
        vdrl();
        gct();
        aboRh();

        return rootview;
    }

    private void initializations(View view)
    {
        mhu_toolbar = (Toolbar) view.findViewById(R.id.mhu_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mhu_toolbar);
        mhu_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.closeAppDialog(getActivity());
            }
        });

        setHasOptionsMenu(true);

        prefManager=new PrefManager(getActivity());

        checkboxCompleteBloodCountHomogram=(CheckBox)view.findViewById(R.id.checkbox_complete_blood_count_homogram);

        completeBloodCountHomogramLinearlayout=(LinearLayout)view.findViewById(R.id.complete_blood_count_homogram_linearlayout);
        edtCbbHgmReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_cbb_hgm_reference_value);
        edtCbbHgmReading=(TextInputEditText)view.findViewById(R.id.edt_cbb_hgm_reading);

        hbLinearlayout=(LinearLayout)view.findViewById(R.id.hb_linearlayout);
        edtHbReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_hb_reference_value);
        edtHbReading=(TextInputEditText)view.findViewById(R.id.edt_hb_reading);

        tlcLinearlayout=(LinearLayout)view.findViewById(R.id.tlc_linearlayout);
        edtTlcReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_tlc_reference_value);
        edtTlcReading=(TextInputEditText)view.findViewById(R.id.edt_tlc_reading);

        dlcLinearlayout=(LinearLayout)view.findViewById(R.id.dlc_linearlayout);
        edtDlcReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_dlc_reference_value);
        edtDlcReading=(TextInputEditText)view.findViewById(R.id.edt_dlc_reading);

        pcvLinearlayout=(LinearLayout)view.findViewById(R.id.pcv_linearlayout);
        edtPcvReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_pcv_reference_value);
        edtPcvReading=(TextInputEditText)view.findViewById(R.id.edt_pcv_reading);

        rbcLinearlayout=(LinearLayout)view.findViewById(R.id.rbc_linearlayout);
        edtRbcReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_rbc_reference_value);
        edtRbcReading=(TextInputEditText)view.findViewById(R.id.edt_rbc_reading);

        plateletCountLinearlayout=(LinearLayout)view.findViewById(R.id.platelet_count_linearlayout);
        edtPlateletCountReferencevalue=(TextInputEditText)view.findViewById(R.id.edt_platelet_count_reference_value);
        edtPlateletCountReading=(TextInputEditText)view.findViewById(R.id.edt_platelet_count_reading);

        mcvMchMchcLinearlayout=(LinearLayout)view.findViewById(R.id.mcv_mch_mchc_linearlayout);
        edtMcvMchMchcReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_mcv_mch_mchc_reference_value);
        edtMcvMchMchcReading=(TextInputEditText)view.findViewById(R.id.edt_mcv_mch_mchc_reading);

        esrLinearlayout=(LinearLayout)view.findViewById(R.id.esr_linearlayout);
        edtEsrReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_esr_reference_value);
        edtEsrReading=(TextInputEditText)view.findViewById(R.id.edt_esr_reading);

        aecLinearlayout=(LinearLayout)view.findViewById(R.id.aec_linearlayout);
        edtAecReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_aec_reference_value);
        edtAecReading=(TextInputEditText)view.findViewById(R.id.edt_aec_reading);


        checkboxLiverFunctionTest=(CheckBox)view.findViewById(R.id.checkbox_liver_function_test);
        dbilLinearlayout=(LinearLayout)view.findViewById(R.id.dbil_linearlayout);
        edtDBilReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_d_bil_reference_value);
        edtDBilReading=(TextInputEditText)view.findViewById(R.id.edt_d_bil_reading);

        idbilLinearlayout=(LinearLayout)view.findViewById(R.id.idbil_linearlayout);
        edtIdBilReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_id_bil_reference_value);
        edtIdBilReading=(TextInputEditText)view.findViewById(R.id.edt_id_bil_reading);

        tbilLinearlayout=(LinearLayout)view.findViewById(R.id.tbil_linearlayout);
        edtTBilReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_t_bil_reference_value);
        edtTBilReading=(TextInputEditText)view.findViewById(R.id.edt_t_bil_reading);

        tprotLinearlayout=(LinearLayout)view.findViewById(R.id.t_prot_linearlayout);
        edtTProtReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_t_prot_reference_value);
        edtTProtReading=(TextInputEditText)view.findViewById(R.id.edt_t_prot_reading);

        agRatLinearlayout=(LinearLayout)view.findViewById(R.id.agRat_linearlayout);
        edtAgRatReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_ag_rat_reference_value);
        edtAgRatReading=(TextInputEditText)view.findViewById(R.id.edt_ag_rat_reading);

        altLinearlayout=(LinearLayout)view.findViewById(R.id.alt_linearlayout);
        edtAltReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_alt_reference_value);
        edtAltReading=(TextInputEditText)view.findViewById(R.id.edt_alt_reading);

        astLinearlayout=(LinearLayout)view.findViewById(R.id.ast_linearlayout);
        edtAstReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_ast_reference_value);
        edtAstReading=(TextInputEditText)view.findViewById(R.id.edt_ast_reading);

        alpLinearlayout=(LinearLayout)view.findViewById(R.id.alp_linearlayout);
        edtAlpReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_alp_reference_value);
        edtAlpReading=(TextInputEditText)view.findViewById(R.id.edt_alp_reading);

        compLftLinearlayout=(LinearLayout)view.findViewById(R.id.comp_lft_linearlayout);
        edtCompLftReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_comp_lft_reference_value);
        edtCompLftReading=(TextInputEditText)view.findViewById(R.id.edt_comp_lft_reading);


        ggtLinearlayout=(LinearLayout)view.findViewById(R.id.ggt_linearlayout);
        edtGgtReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_ggt_reference_value);
        edtGggtReading=(TextInputEditText)view.findViewById(R.id.edt_ggt_reading);

        albLinearlayout=(LinearLayout)view.findViewById(R.id.alb_linearlayout);
        edtAlbReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_alb_reference_value);
        edtAlbReading=(TextInputEditText)view.findViewById(R.id.edt_alb_reading);

        checkboxKidneyFunctionTest=(CheckBox)view.findViewById(R.id.checkbox_kidney_function_test);
        ureaLinearlayout=(LinearLayout)view.findViewById(R.id.urea_linearlayout);
        edtUreaReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_urea_reference_value);
        edtUreaReading=(TextInputEditText)view.findViewById(R.id.edt_urea_reading);

        creatLinearlayout=(LinearLayout)view.findViewById(R.id.creat_linearlayout);
        edtCreatReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_creat_reference_value);
        edtCreatReading=(TextInputEditText)view.findViewById(R.id.edt_creat_reading);

        uricAcidLinearlayout=(LinearLayout)view.findViewById(R.id.uric_acid_linearlayout);
        edtUricAcidReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_uric_acid_reference_value);
        edtUricAcidReading=(TextInputEditText)view.findViewById(R.id.edt_uric_acid_reading);

        electrolyteLinearlayout=(LinearLayout)view.findViewById(R.id.electrolyte_linearlayout);
        edtElectrolyteReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_electrolyte_reference_value);
        edtElectrolyteReading=(TextInputEditText)view.findViewById(R.id.edt_electrolyte_reading);

        liverFunctionTprotLinearlayout=(LinearLayout)view.findViewById(R.id.liverfunction_tprot_linearlayout);
        edtLiverfunctionTprotReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_liverfunction_tprot_reference_value);
        edtLiverfunctionTprotReading=(TextInputEditText)view.findViewById(R.id.edt_liverfunction_tprot_reading);

        a1bKidneyFunctionLinearlayout=(LinearLayout)view.findViewById(R.id.a1b_kidney_function_linearlayout);
        edtA1bKidneyFunctionReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_a1b_kidney_function_reference_value);
        edtA1bKidneyFunctionReading=(TextInputEditText)view.findViewById(R.id.edt_a1b_kidney_function_reading);

        a1bCrRatioLinearlayout=(LinearLayout)view.findViewById(R.id.a1bCrRatio_linearlayout);
        edtA1bCrRatioReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_a1b_cr_ratio_reference_value);
        edtA1bCrRatioReading=(TextInputEditText)view.findViewById(R.id.edt_a1b_cr_ratio_reading);

        microAlbuminLinearlayout=(LinearLayout)view.findViewById(R.id.micro_albumin_linearlayout);
        edtMicroAlbuminReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_micro_albumin_reference_value);
        edtMicroAlbuminReading=(TextInputEditText)view.findViewById(R.id.edt_micro_albumin_reading);

        kidneyCompKftLinearlayout=(LinearLayout)view.findViewById(R.id.kidney_comp_kft_linearlayout);
        edtKidneyCompKftReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_kidney_comp_kft_reference_value);
        edtKidneyCompKftReading=(TextInputEditText)view.findViewById(R.id.edt_kidney_comp_kft_reading);

        checkboxLipidProfile=(CheckBox)view.findViewById(R.id.checkbox_lipid_profile);
        cholesterolLinearlayout=(LinearLayout)view.findViewById(R.id.cholesterol_linearlayout);
        edtCholesterolReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_cholesterol_reference_value);
        edtCholesterolReading=(TextInputEditText)view.findViewById(R.id.edt_cholesterol_reading);

        tagLinearlayout=(LinearLayout)view.findViewById(R.id.tag_linearlayout);
        edtTagReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_tag_reference_value);
        edtTagReading=(TextInputEditText)view.findViewById(R.id.edt_tag_reading);

        hdlLinearlayout=(LinearLayout)view.findViewById(R.id.hdl_linearlayout);
        edtHdlReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_hdl_reference_value);
        edtHdlReading=(TextInputEditText)view.findViewById(R.id.edt_hdl_reading);

        completeLipidProfileLinearlayout=(LinearLayout)view.findViewById(R.id.complete_lipid_profile_linearlayout);
        edtCompLftReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_complete_lipid_profile_reference_value);
        edtCompLftReading=(TextInputEditText)view.findViewById(R.id.edt_complete_lipid_profile_reading);


        checkboxGlucoseProfile=(CheckBox)view.findViewById(R.id.checkbox_glucose_profile);
        fbgLinearlayout=(LinearLayout)view.findViewById(R.id.fbg_linearlayout);
        edtFbgReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_fbg_reference_value);
        edtFbgReading=(TextInputEditText)view.findViewById(R.id.edt_fbg_reading);

        twohrLinearlayout=(LinearLayout)view.findViewById(R.id.twohr_linearlayout);
        edtTwohrReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_twohr_reference_value);
        edtTwohrReading=(TextInputEditText)view.findViewById(R.id.edt_twohr_reading);

        randomLinearlayout=(LinearLayout)view.findViewById(R.id.random_linearlayout);
        edtRandomReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_random_reference_value);
        edtRandomReading=(TextInputEditText)view.findViewById(R.id.edt_random_reading);

        glucoseProfileGttLinearlayout=(LinearLayout)view.findViewById(R.id.glucose_profile_gtt_linearlayout);
        edtGlucoseProfileGttReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_glucose_profile_gtt_reference_value);
        edtGlucoseProfileGttReading=(TextInputEditText)view.findViewById(R.id.edt_glucose_profile_gtt_reading);

        hba1cLinearlayout=(LinearLayout)view.findViewById(R.id.hba1c_linearlayout);
        edtHba1cReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_hba1c_reference_value);
        edtHba1cReading=(TextInputEditText)view.findViewById(R.id.edt_hba1c_reading);

        urinaryMicroAlbuminLinearlayout=(LinearLayout)view.findViewById(R.id.urinary_micro_albumin_linearlayout);
        edtUrinaryMicroAlbuminRefrancevalue=(TextInputEditText)view.findViewById(R.id.edt_urinary_micro_albuminrefrancevalue);
        edtUrinaryMicroAlbuminReading=(TextInputEditText)view.findViewById(R.id.edt_urinary_micro_albumin_reading);

        insulinFppLinearlayout=(LinearLayout)view.findViewById(R.id.insulin_fpp_linearlayout);
        edtInsulinFppRefrancevalue=(TextInputEditText)view.findViewById(R.id.edt_insulin_fpp_refrancevalue);
        edtInsulinFppReading=(TextInputEditText)view.findViewById(R.id.edt_insulin_fpp_reading);

        checkboxWidal=(CheckBox)view.findViewById(R.id.checkbox_widal);
        widaltextLinearlayout=(LinearLayout)view.findViewById(R.id.widaltext_linearlayout);
        edtWidaltextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_widaltext_reference_value);
        edtWidaltextReading=(TextInputEditText)view.findViewById(R.id.edt_widaltext_reading);


        checkboxTyphidot=(CheckBox)view.findViewById(R.id.checkbox_typhidot);
        typhidotTextLinearlayout=(LinearLayout)view.findViewById(R.id.typhidot_text_linearlayout);
        edtTyphidotTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_typhidot_text_reference_value);
        edtTyphidotTextReading=(TextInputEditText)view.findViewById(R.id.edt_typhidot_text_reading);

        checkboxMalariaSerology=(CheckBox)view.findViewById(R.id.checkbox_malaria_serology);
        malariaSerologyTextLinearlayout=(LinearLayout)view.findViewById(R.id.malaria_serology_text_linearlayout);
        edtMalariaSerologyTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_malaria_serology_text_reference_value);
        edtMalariaSerologyTextReading=(TextInputEditText)view.findViewById(R.id.edt_malaria_serology_text_reading);

        checkboxRaFactor=(CheckBox)view.findViewById(R.id.checkbox_ra_factor);
        raFactorTextLinearlayout=(LinearLayout)view.findViewById(R.id.ra_factor_text_linearlayout);
        edtRaFactorTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_ra_factor_text_reference_value);
        edtRaFactorTextReading=(TextInputEditText)view.findViewById(R.id.edt_ra_factor_text_reading);

        checkboxHbsag=(CheckBox)view.findViewById(R.id.checkbox_hbsag);
        hbsagTextLinearlayout=(LinearLayout)view.findViewById(R.id.hbsag_text_linearlayout);
        edtHbsagTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_hbsag_text_reference_value);
        edtHbsagTextReading=(TextInputEditText)view.findViewById(R.id.edt_hbsag_text_reading);


        checkboxUrineExamination=(CheckBox)view.findViewById(R.id.checkbox_urine_examination);
        routineMicrosocopicLinearlayout=(LinearLayout)view.findViewById(R.id.routine_microsocopic_linearlayout);
        edtRoutineMicrosocopicReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_routine_microsocopic_reference_value);
        edtRoutineMicrosocopicReading=(TextInputEditText)view.findViewById(R.id.edt_routine_microsocopic_reading);

        proteinLinearlayout=(LinearLayout)view.findViewById(R.id.protein_linearlayout);
        edtProteinReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_protein_reference_value);
        edtProteinReading=(TextInputEditText)view.findViewById(R.id.edt_protein_reading);

        sugarLinearlayout=(LinearLayout)view.findViewById(R.id.sugar_linearlayout);
        edtSugarReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_sugar_reference_value);
        edtSugarReading=(TextInputEditText)view.findViewById(R.id.edt_sugar_reading);

        ketoneBodiesLinearlayout=(LinearLayout)view.findViewById(R.id.ketone_bodies_linearlayout);
        edtKetoneBodiesReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_ketone_bodies_reference_value);
        edtKetoneBodiesReading=(TextInputEditText)view.findViewById(R.id.edt_ketone_bodies_reading);

        bileSaltBilePigmentsLinearlayout=(LinearLayout)view.findViewById(R.id.bile_salt_bile_pigments_linearlayout);
        edtBileSaltBilePigmentsReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_bile_salt_bile_pigments_reference_value);
        edtBileSaltBilePigmentsReading=(TextInputEditText)view.findViewById(R.id.edt_bile_salt_bile_pigments_reading);

        urinePregnancyTestLinearlayout=(LinearLayout)view.findViewById(R.id.urine_pregnancy_test_linearlayout);
        edtUrinePregnancyTestReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_urine_pregnancy_test_reference_value);
        edtUrinePregnancyTestReading=(TextInputEditText)view.findViewById(R.id.edt_urine_pregnancy_test_reading);

        checkboxCrp=(CheckBox)view.findViewById(R.id.checkbox_crp);
        crpTextLinearlayout=(LinearLayout)view.findViewById(R.id.crp_text_linearlayout);
        edtCrpTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_crp_text_reference_value);
        edtCrpTextReading=(TextInputEditText)view.findViewById(R.id.edt_crp_text_reading);

        checkboxAntiHcv=(CheckBox)view.findViewById(R.id.checkbox_anti_hcv);
        antiHcvLinearlayout=(LinearLayout)view.findViewById(R.id.anti_hcv_linearlayout);
        edtAntiHcvTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_anti_hcv_text_reference_value);
        edtAntiHcvTextReading=(TextInputEditText)view.findViewById(R.id.edt_anti_hcv_text_reading);

        checkboxHiv=(CheckBox)view.findViewById(R.id.checkbox_hiv);
        hivTextLinearlayout=(LinearLayout)view.findViewById(R.id.hiv_text_linearlayout);
        edtHivTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_hiv_text_reference_value);
        edtHivTextReading=(TextInputEditText)view.findViewById(R.id.edt_hiv_text_reading);

        checkboxVdrl=(CheckBox)view.findViewById(R.id.checkbox_vdrl);
        vdrlTextLinearlayout=(LinearLayout)view.findViewById(R.id.vdrl_text_linearlayout);
        edtVdrlTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_vdrl_text_reference_value);
        edtVdrlTextReading=(TextInputEditText)view.findViewById(R.id.edt_vdrl_text_reading);

        checkboxGct=(CheckBox)view.findViewById(R.id.checkbox_gct);
        gctTextLinearlayout=(LinearLayout)view.findViewById(R.id.gct_text_linearlayout);
        edtGctTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_gct_text_reference_value);
        edtGctTextReading=(TextInputEditText)view.findViewById(R.id.edt_gct_text_reading);


        checkboxAboRh=(CheckBox)view.findViewById(R.id.checkbox_abo_rh);
        aboRhTextLinearlayout=(LinearLayout)view.findViewById(R.id.abo_rh_text_linearlayout);
        edtAboRhTextReferenceValue=(TextInputEditText)view.findViewById(R.id.edt_abo_rh_text_reference_value);
        edtAboRhTextReading=(TextInputEditText)view.findViewById(R.id.edt_abo_rh_text_reading);



        btnSaveMhu=(Button)view.findViewById(R.id.btn_save);
    }

    private void saveClickListner()
    {
        btnSaveMhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    setMHUTestData();

                webAddTestAttributeValues(getActivity(), new ApiResponseListener() {
                    @Override
                    public void onSuccess(String message) {
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        TestAdvised testAdvised = TestAdvised.getInstance();
                        fragmentTransaction.replace(R.id.framelayout, testAdvised).addToBackStack(null).commit();

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getActivity(),"Insert atleast one test or check properly",Toast.LENGTH_SHORT).show();
                    }
                },jsonoarray);



            }
        });
    }

    private void setMHUTestData()
    {


        //if(checkboxCompleteBloodCountHomogram.isChecked())
        {

            JSONArray jsonArray=new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "1");
                jsonobj.put("reference_value", edtCbbHgmReferenceValue.getText());
                jsonobj.put("reading", edtCbbHgmReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"tostring",Toast.LENGTH_SHORT).show();
            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "2");
                jsonobj.put("reference_value", edtHbReferenceValue.getText());
                jsonobj.put("reading", edtHbReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "3");
                jsonobj.put("reference_value", edtTlcReferenceValue.getText());
                jsonobj.put("reading", edtTlcReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "4");
                jsonobj.put("reference_value", edtDlcReferenceValue.getText());
                jsonobj.put("reading", edtDlcReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "5");
                jsonobj.put("reference_value", edtPcvReferenceValue.getText());
                jsonobj.put("reading", edtPcvReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "6");
                jsonobj.put("reference_value", edtRbcReferenceValue.getText());
                jsonobj.put("reading", edtRbcReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "7");
                jsonobj.put("reference_value", edtPlateletCountReferencevalue.getText());
                jsonobj.put("reading", edtPlateletCountReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "8");
                jsonobj.put("reference_value", edtMcvMchMchcReferenceValue.getText());
                jsonobj.put("reading", edtMcvMchMchcReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "9");
                jsonobj.put("reference_value", edtEsrReferenceValue.getText());
                jsonobj.put("reading", edtEsrReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "10");
                jsonobj.put("reference_value", edtAecReferenceValue.getText());
                jsonobj.put("reading", edtAecReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put(" 1",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       // if(checkboxLiverFunctionTest.isChecked())
        {
            JSONArray jsonArray=new JSONArray();

            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "11");
                jsonobj.put("reference_value", edtDBilReferenceValue.getText());
                jsonobj.put("reading", edtDBilReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "12");
                jsonobj.put("reference_value", edtIdBilReferenceValue.getText());
                jsonobj.put("reading", edtIdBilReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "13");
                jsonobj.put("reference_value", edtTBilReferenceValue.getText());
                jsonobj.put("reading", edtTBilReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "14");
                jsonobj.put("reference_value", edtLiverfunctionTprotReferenceValue.getText());
                jsonobj.put("reading", edtLiverfunctionTprotReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "15");
                jsonobj.put("reference_value", edtAgRatReferenceValue.getText());
                jsonobj.put("reading", edtAgRatReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "16");
                jsonobj.put("reference_value", edtAltReferenceValue.getText());
                jsonobj.put("reading", edtAltReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "17");
                jsonobj.put("reference_value", edtAstReferenceValue.getText());
                jsonobj.put("reading", edtAstReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("visit_master_id", MainActivity.Visit_ID);
            jsonobj.put("map_test_attribute_id", "18");
            jsonobj.put("reference_value", edtAlpReferenceValue.getText());
            jsonobj.put("reading", edtAlpReading.getText());
            jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "19");
                jsonobj.put("reference_value", edtCompLftReferenceValue.getText());
                jsonobj.put("reading", edtCompLftReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "20");
                jsonobj.put("reference_value", edtGgtReferenceValue.getText());
                jsonobj.put("reading", edtGggtReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "21");
                jsonobj.put("reference_value", edtAlbReferenceValue.getText());
                jsonobj.put("reading", edtAlbReading.getText());
                jsonArray.put(jsonobj);
            }
            catch (Exception e)
            {

            }


            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("2",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //if(checkboxKidneyFunctionTest.isChecked())
        {
            JSONArray jsonArray=new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "22");
                jsonobj.put("reference_value", edtUreaReferenceValue.getText());
                jsonobj.put("reading", edtUreaReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }
            try {

                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "23");
                jsonobj.put("reference_value", edtCreatReferenceValue.getText());
                jsonobj.put("reading", edtCreatReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "24");
                jsonobj.put("reference_value", edtUricAcidReferenceValue.getText());
                jsonobj.put("reading", edtUricAcidReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "25");
                jsonobj.put("reference_value", edtElectrolyteReferenceValue.getText());
                jsonobj.put("reading", edtElectrolyteReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "26");
                jsonobj.put("reference_value", edtTProtReferenceValue.getText());
                jsonobj.put("reading", edtTProtReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

           try {
                JSONObject jsonobj = new JSONObject();
               jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "27");
                jsonobj.put("reference_value", edtA1bKidneyFunctionReferenceValue.getText());
                jsonobj.put("reading", edtA1bKidneyFunctionReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "28");
                jsonobj.put("reference_value", edtA1bCrRatioReferenceValue.getText());
                jsonobj.put("reading", edtA1bCrRatioReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "29");
                jsonobj.put("reference_value", edtMicroAlbuminReferenceValue.getText());
                jsonobj.put("reading", edtMicroAlbuminReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "30");
                jsonobj.put("reference_value", edtKidneyCompKftReferenceValue.getText());
                jsonobj.put("reading", edtCompLftReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("3",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


       // if(checkboxLipidProfile.isChecked())
       {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "31");
                jsonobj.put("reference_value", edtCholesterolReferenceValue.getText());
                jsonobj.put("reading", edtCholesterolReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "32");
                jsonobj.put("reference_value", edtTagReferenceValue.getText());
                jsonobj.put("reading", edtTagReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "33");
                jsonobj.put("reference_value", edtHdlReferenceValue.getText());
                jsonobj.put("reading", edtHdlReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "34");
                jsonobj.put("reference_value", edtCompleteLipidProfileReferenceValue.getText());
                jsonobj.put("reading", edtCompleteLipidProfileReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("4",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //if(checkboxGlucoseProfile.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "35");
                jsonobj.put("reference_value", edtFbgReferenceValue.getText());
                jsonobj.put("reading", edtFbgReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "36");
                jsonobj.put("reference_value", edtTwohrReferenceValue.getText());
                jsonobj.put("reading", edtTwohrReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }



            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "37");
                jsonobj.put("reference_value", edtRandomReferenceValue.getText());
                jsonobj.put("reading", edtRandomReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "38");
                jsonobj.put("reference_value", edtGlucoseProfileGttReferenceValue.getText());
                jsonobj.put("reading", edtGlucoseProfileGttReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "39");
                jsonobj.put("reference_value",edtHba1cReferenceValue.getText());
                jsonobj.put("reading", edtHba1cReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "40");
                jsonobj.put("reference_value",edtUrinaryMicroAlbuminRefrancevalue.getText());
                jsonobj.put("reading", edtUrinaryMicroAlbuminReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "41");
                jsonobj.put("reference_value",edtInsulinFppRefrancevalue.getText());
                jsonobj.put("reading", edtInsulinFppReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("5",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        //if(checkboxWidal.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "42");
                jsonobj.put("reference_value", edtWidaltextReferenceValue.getText());
                jsonobj.put("reading", edtWidaltextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("6",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //if(checkboxTyphidot.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "43");
                jsonobj.put("reference_value", edtTyphidotTextReferenceValue.getText());
                jsonobj.put("reading", edtTyphidotTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("7",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //if(checkboxMalariaSerology.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "44");
                jsonobj.put("reference_value", edtMalariaSerologyTextReferenceValue.getText());
                jsonobj.put("reading", edtMalariaSerologyTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("8",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


       // if(checkboxRaFactor.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "45");
                jsonobj.put("reference_value", edtRaFactorTextReferenceValue.getText());
                jsonobj.put("reading", edtRaFactorTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("9",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


            //if(checkboxHbsag.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "46");
                jsonobj.put("reference_value", edtHbsagTextReferenceValue.getText());
                jsonobj.put("reading", edtHbsagTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("10",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //if(checkboxUrineExamination.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "47");
                jsonobj.put("reference_value", edtRoutineMicrosocopicReferenceValue.getText());
                jsonobj.put("reading", edtRoutineMicrosocopicReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "48");
                jsonobj.put("reference_value", edtProteinReferenceValue.getText());
                jsonobj.put("reading", edtProteinReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "49");
                jsonobj.put("reference_value", edtSugarReferenceValue.getText());
                jsonobj.put("reading", edtSugarReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "50");
                jsonobj.put("reference_value", edtKetoneBodiesReferenceValue.getText());
                jsonobj.put("reading", edtKetoneBodiesReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "51");
                jsonobj.put("reference_value", edtBileSaltBilePigmentsReferenceValue.getText());
                jsonobj.put("reading", edtBileSaltBilePigmentsReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "52");
                jsonobj.put("reference_value", edtUrinePregnancyTestReferenceValue.getText());
                jsonobj.put("reading", edtUrinePregnancyTestReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("11",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


       // if(checkboxCrp.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "53");
                jsonobj.put("reference_value",edtCrpTextReferenceValue .getText());
                jsonobj.put("reading", edtCrpTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }


            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("12",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


       // if(checkboxAntiHcv.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "54");
                jsonobj.put("reference_value",edtAntiHcvTextReferenceValue .getText());
                jsonobj.put("reading", edtAntiHcvTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("13",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //if(checkboxHiv.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "55");
                jsonobj.put("reference_value", edtHivTextReferenceValue.getText());
                jsonobj.put("reading", edtHivTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("14",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //if(checkboxVdrl.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "56");
                jsonobj.put("reference_value", edtVdrlTextReferenceValue.getText());
                jsonobj.put("reading", edtVdrlTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("15",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //if(checkboxGct.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "57");
                jsonobj.put("reference_value", edtGctTextReferenceValue.getText());
                jsonobj.put("reading", edtGctTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("16",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //if(checkboxAboRh.isChecked())
        {
            JSONArray jsonArray = new JSONArray();
            try {
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("visit_master_id", MainActivity.Visit_ID);
                jsonobj.put("map_test_attribute_id", "58");
                jsonobj.put("reference_value", edtAboRhTextReferenceValue.getText());
                jsonobj.put("reading", edtAboRhTextReading.getText());
                jsonArray.put(jsonobj);
            } catch (Exception e) {

            }

            try {
                JSONObject jsonobjmain=new JSONObject();
                jsonoarray.put(jsonobjmain);
                jsonobjmain.put("17",jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void setSelectedBloodCount()
    {
        checkboxCompleteBloodCountHomogram.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selected_blood_count_homogram="Y";
                    completeBloodCountHomogramLinearlayout.setVisibility(View.VISIBLE);
                    hbLinearlayout.setVisibility(View.VISIBLE);
                    tlcLinearlayout.setVisibility(View.VISIBLE);
                    dlcLinearlayout.setVisibility(View.VISIBLE);
                    pcvLinearlayout.setVisibility(View.VISIBLE);
                    rbcLinearlayout.setVisibility(View.VISIBLE);
                    plateletCountLinearlayout.setVisibility(View.VISIBLE);
                    mcvMchMchcLinearlayout.setVisibility(View.VISIBLE);
                    esrLinearlayout.setVisibility(View.VISIBLE);
                    aecLinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    selected_blood_count_homogram="N";
                    completeBloodCountHomogramLinearlayout.setVisibility(View.GONE);
                    hbLinearlayout.setVisibility(View.GONE);
                    tlcLinearlayout.setVisibility(View.GONE);
                    dlcLinearlayout.setVisibility(View.GONE);
                    pcvLinearlayout.setVisibility(View.GONE);
                    rbcLinearlayout.setVisibility(View.GONE);
                    plateletCountLinearlayout.setVisibility(View.GONE);
                    mcvMchMchcLinearlayout.setVisibility(View.GONE);
                    esrLinearlayout.setVisibility(View.GONE);
                    aecLinearlayout.setVisibility(View.GONE);

                }
            }
        });

    }

    private void liverFunctionTest()
    {
        checkboxLiverFunctionTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    liver_function_test="Y";
                    dbilLinearlayout.setVisibility(View.VISIBLE);
                    idbilLinearlayout.setVisibility(View.VISIBLE);
                    tbilLinearlayout.setVisibility(View.VISIBLE);
                    tprotLinearlayout.setVisibility(View.VISIBLE);
                    agRatLinearlayout.setVisibility(View.VISIBLE);
                    altLinearlayout.setVisibility(View.VISIBLE);
                    astLinearlayout.setVisibility(View.VISIBLE);
                    alpLinearlayout.setVisibility(View.VISIBLE);
                    compLftLinearlayout.setVisibility(View.VISIBLE);
                    ggtLinearlayout.setVisibility(View.VISIBLE);
                    albLinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    liver_function_test="N";
                    dbilLinearlayout.setVisibility(View.GONE);
                    idbilLinearlayout.setVisibility(View.GONE);
                    tbilLinearlayout.setVisibility(View.GONE);
                    tprotLinearlayout.setVisibility(View.GONE);
                    agRatLinearlayout.setVisibility(View.GONE);
                    altLinearlayout.setVisibility(View.GONE);
                    astLinearlayout.setVisibility(View.GONE);
                    alpLinearlayout.setVisibility(View.GONE);
                    compLftLinearlayout.setVisibility(View.GONE);
                    ggtLinearlayout.setVisibility(View.GONE);
                    albLinearlayout.setVisibility(View.GONE);


                }
            }
        });
    }

    private void kidneyFunctionTest()
    {
        checkboxKidneyFunctionTest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    kidney_function_test="Y";
                    ureaLinearlayout.setVisibility(View.VISIBLE);
                    creatLinearlayout.setVisibility(View.VISIBLE);
                    uricAcidLinearlayout.setVisibility(View.VISIBLE);
                    electrolyteLinearlayout.setVisibility(View.VISIBLE);
                    liverFunctionTprotLinearlayout.setVisibility(View.VISIBLE);
                    a1bKidneyFunctionLinearlayout.setVisibility(View.VISIBLE);
                    a1bCrRatioLinearlayout.setVisibility(View.VISIBLE);
                    microAlbuminLinearlayout.setVisibility(View.VISIBLE);
                    kidneyCompKftLinearlayout.setVisibility(View.VISIBLE);



                }
                else
                {
                    kidney_function_test="N";
                    ureaLinearlayout.setVisibility(View.GONE);
                    creatLinearlayout.setVisibility(View.GONE);
                    uricAcidLinearlayout.setVisibility(View.GONE);
                    electrolyteLinearlayout.setVisibility(View.GONE);
                    liverFunctionTprotLinearlayout.setVisibility(View.GONE);
                    a1bKidneyFunctionLinearlayout.setVisibility(View.GONE);
                    a1bCrRatioLinearlayout.setVisibility(View.GONE);
                    microAlbuminLinearlayout.setVisibility(View.GONE);
                    kidneyCompKftLinearlayout.setVisibility(View.GONE);



                }
            }
        });
    }

    private void lipidProfile()
    {
        checkboxLipidProfile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    liquid_profile="Y";
                    cholesterolLinearlayout.setVisibility(View.VISIBLE);
                    tagLinearlayout.setVisibility(View.VISIBLE);
                    hdlLinearlayout.setVisibility(View.VISIBLE);
                    completeLipidProfileLinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    liquid_profile="N";
                    cholesterolLinearlayout.setVisibility(View.GONE);
                    tagLinearlayout.setVisibility(View.GONE);
                    hdlLinearlayout.setVisibility(View.GONE);
                    completeLipidProfileLinearlayout.setVisibility(View.GONE);


                }
            }
        });
    }

    private void glucoseProfile()
    {
        checkboxGlucoseProfile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    glucose_profile="Y";
                    fbgLinearlayout.setVisibility(View.VISIBLE);
                    twohrLinearlayout.setVisibility(View.VISIBLE);
                    randomLinearlayout.setVisibility(View.VISIBLE);
                    glucoseProfileGttLinearlayout.setVisibility(View.VISIBLE);
                    hba1cLinearlayout.setVisibility(View.VISIBLE);
                    urinaryMicroAlbuminLinearlayout.setVisibility(View.VISIBLE);
                    insulinFppLinearlayout.setVisibility(View.VISIBLE);


                }
                else
                {
                    glucose_profile="N";
                    fbgLinearlayout.setVisibility(View.GONE);
                    twohrLinearlayout.setVisibility(View.GONE);
                    randomLinearlayout.setVisibility(View.GONE);
                    glucoseProfileGttLinearlayout.setVisibility(View.GONE);
                    hba1cLinearlayout.setVisibility(View.GONE);
                    urinaryMicroAlbuminLinearlayout.setVisibility(View.GONE);
                    insulinFppLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void widal()
    {
        checkboxWidal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    widal="Y";
                    widaltextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    widal="N";
                    widaltextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

   private void typhidot()
    {
        checkboxTyphidot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    typhidot="Y";
                    typhidotTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    typhidot="N";
                    typhidotTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void malariaSerology()
    {
        checkboxMalariaSerology.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    malaria_serology="Y";
                    malariaSerologyTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    malaria_serology="N";
                    malariaSerologyTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void raFactor()
    {
        checkboxRaFactor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ra_factor="Y";
                    raFactorTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    ra_factor="N";
                    raFactorTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void hbsag()
    {
        checkboxHbsag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    hbsag="Y";
                    hbsagTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    hbsag="N";
                    hbsagTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void urineExamination()
    {
        checkboxUrineExamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    urine_examination="Y";
                    routineMicrosocopicLinearlayout.setVisibility(View.VISIBLE);
                    proteinLinearlayout.setVisibility(View.VISIBLE);
                    sugarLinearlayout.setVisibility(View.VISIBLE);
                    ketoneBodiesLinearlayout.setVisibility(View.VISIBLE);
                    bileSaltBilePigmentsLinearlayout.setVisibility(View.VISIBLE);
                    urinePregnancyTestLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    urine_examination="N";
                    routineMicrosocopicLinearlayout.setVisibility(View.GONE);
                    proteinLinearlayout.setVisibility(View.GONE);
                    sugarLinearlayout.setVisibility(View.GONE);
                    ketoneBodiesLinearlayout.setVisibility(View.GONE);
                    bileSaltBilePigmentsLinearlayout.setVisibility(View.GONE);
                    urinePregnancyTestLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void crp()
    {
        checkboxCrp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    crp="Y";
                    crpTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    crp="N";
                    crpTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void antiHcv()
    {
        checkboxAntiHcv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    antihcv="Y";
                    antiHcvLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    antihcv="N";
                    antiHcvLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void hiv()
    {
        checkboxHiv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    hiv="Y";
                    hivTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    hiv="N";
                    hivTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void vdrl()
    {
        checkboxVdrl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    vdrl="Y";
                    vdrlTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    vdrl="N";
                    vdrlTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void gct()
    {
        checkboxGct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    gct="Y";
                    gctTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    gct="N";
                    gctTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void aboRh()
    {
        checkboxAboRh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    abo_rh="Y";
                    aboRhTextLinearlayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    abo_rh="N";
                    aboRhTextLinearlayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (item.getItemId())
        {
            case R.id.logout:
                prefManager.setLogOut();
                Intent i= new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();



                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private boolean checkValidation()
    {
        boolean response=true;

        if (etBloodGlucose.getText().trim().length() == 0)
        {
            etblood_glucoseTextLayout.setErrorEnabled(true);
            etblood_glucoseTextLayout.setErrorTextAppearance(R.style.error);
            etblood_glucoseTextLayout.setError("Enter Blood Glucose");
            response = false;
        } else {
            etblood_glucoseTextLayout.setErrorEnabled(false);
            etblood_glucoseTextLayout.setError(null);
        }


        if (etcreatine.getText().trim().length() == 0)
        {
            creatineTextLayout.setErrorEnabled(true);
            creatineTextLayout.setErrorTextAppearance(R.style.error);
            creatineTextLayout.setError("Enter creatine");
            response = false;
        } else {
            creatineTextLayout.setErrorEnabled(false);
            creatineTextLayout.setError(null);
        }


        if (ethemogram.getText().trim().length() == 0)
        {
            hemogramTextLayout.setErrorEnabled(true);
            hemogramTextLayout.setErrorTextAppearance(R.style.error);
            hemogramTextLayout.setError("Enter hemogram");
            response = false;
        } else {
            hemogramTextLayout.setErrorEnabled(false);
            hemogramTextLayout.setError(null);
        }

        if (etsgot.getText().trim().length() == 0)
        {
            sgotTextLayout.setErrorEnabled(true);
            sgotTextLayout.setErrorTextAppearance(R.style.error);
            sgotTextLayout.setError("Enter sgot");
            response = false;
        } else {
            sgotTextLayout.setErrorEnabled(false);
            sgotTextLayout.setError(null);
        }


        if (etsgpt.getText().trim().length() == 0)
        {
            sgptTextLayout.setErrorEnabled(true);
            sgptTextLayout.setErrorTextAppearance(R.style.error);
            sgptTextLayout.setError("Enter sgpt");
            response = false;
        } else {
            sgptTextLayout.setErrorEnabled(false);
            sgptTextLayout.setError(null);
        }


        if (eturea.getText().trim().length() == 0)
        {
            ureaTextLayout.setErrorEnabled(true);
            ureaTextLayout.setErrorTextAppearance(R.style.error);
            ureaTextLayout.setError("Enter refrred");
            response = false;
        } else {
            ureaTextLayout.setErrorEnabled(false);
            ureaTextLayout.setError(null);
        }

        return  response;
    }
*/

}
