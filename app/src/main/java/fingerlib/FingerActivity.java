package fingerlib;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gkj.bckhan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.JSUtil;

public class FingerActivity extends AppCompatActivity implements IFeature {

    public void showFragment() {
        FingerFragment fingerFragment = new FingerFragment();
        fingerFragment.show(getFragmentManager(), "fingerFragment");
        fingerFragment.setmFragmentCallBack(new FingerFragment.Callback() {
            @Override
            public void onSuccess() {
                Toast.makeText(FingerActivity.this, "成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError() {
                Toast.makeText(FingerActivity.this, "弹密码框", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void init(AbsMgr arg0, String arg1) {
        // TODO Auto-generated method stub

    }
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @SuppressLint("NewApi")
    @Override
    public String execute(final IWebview pWebview, final String action, final String[] pArgs) {
        // TODO Auto-generated method stub
        //Context context = pWebview.getContext();

        if ("PluginTestFunction".equals(action))
        {
            String CallBackID = pArgs[0];
            JSONArray newArray = new JSONArray();
            newArray.put(pArgs[1]);
            newArray.put(pArgs[2]);
            newArray.put(pArgs[3]);
            newArray.put(pArgs[4]);

            JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);
        }
        else if("PluginTestFunctionArrayArgu".equals(action))
        {
            String ReturnString = null;
            String CallBackID =  pArgs[0];
            JSONArray newArray = null;
            try {
                showFragment();
                newArray = new JSONArray(pArgs[1]);
                String inValue1 = newArray.getString(0);
                String inValue2 = newArray.getString(1);
                String inValue3 = newArray.getString(2);
                String inValue4 = newArray.getString(3);
                ReturnString = inValue1 + "-" + inValue2 + "-" + inValue3 + "-" + inValue4;
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSUtil.execCallback(pWebview, CallBackID, ReturnString, JSUtil.OK, false);

        }else if("PluginTestFunctionSync".equals(action))
        {
            String inValue1 = pArgs[0];
            String inValue2 = pArgs[1];
            String inValue3 = pArgs[2];
            String inValue4 = pArgs[3];
            showFragment();
            String ReturnValue = inValue1 + "-" + inValue2 + "-" + inValue3 + "-" + inValue4;
            return JSUtil.wrapJsVar(ReturnValue,true);

        }else if("PluginTestFunctionSyncArrayArgu".equals(action))
        {
            JSONArray newArray = null;
            JSONObject retJSONObj = null;
            try {
                newArray = new JSONArray(pArgs[0]);
                String inValue1 = newArray.getString(0);
                String inValue2 = newArray.getString(1);
                String inValue3 = newArray.getString(2);
                String inValue4 = newArray.getString(3);

                retJSONObj = new JSONObject();
                retJSONObj.putOpt("RetArgu1", inValue1);
                retJSONObj.putOpt("RetArgu2", inValue2);
                retJSONObj.putOpt("RetArgu3", inValue3);
                retJSONObj.putOpt("RetArgu4", inValue4);

            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            return JSUtil.wrapJsVar(retJSONObj);

        }
        return "11111";
    }

    @Override
    public void dispose(String arg0) {
        // TODO Auto-generated method stub

    }


}
