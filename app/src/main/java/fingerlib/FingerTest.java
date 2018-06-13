package fingerlib;

import fingerlib.base.BaseFingerprint;
import io.dcloud.PandoraEntry;
import io.dcloud.application.DCloudApplication;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.BaseFeature;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.JSUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.gkj.bckhan.R;


public class FingerTest implements IFeature {
    private FingerprintIdentify mFingerprintIdentify;
    private static final int MAX_AVAILABLE_TIMES = 3;
    String ReturnString = null;

    @Override
    public void init(AbsMgr arg0, String arg1) {
        // TODO Auto-generated method stub
        mFingerprintIdentify = new FingerprintIdentify(DCloudApplication.getInstance(), new BaseFingerprint.FingerprintIdentifyExceptionListener() {
            @Override
            public void onCatchException(Throwable exception) {
//                    Toast.makeText(getActivity(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void dispose(String s) {

    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @SuppressLint("NewApi")
    @Override
    public String execute(final IWebview pWebview, final String action, final String[] pArgs) {
        // TODO Auto-generated method stub

        mFingerprintIdentify = new FingerprintIdentify(DCloudApplication.getInstance(), new BaseFingerprint.FingerprintIdentifyExceptionListener() {
            @Override
            public void onCatchException(Throwable exception) {
//                    Toast.makeText(getActivity(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        if (mFingerprintIdentify != null && mFingerprintIdentify.isHardwareEnable()) {
            if (!mFingerprintIdentify.isRegisteredFingerprint()) {
                String CallBackID = pArgs[0];
                JSONArray newArray = new JSONArray();
                newArray.put("请先进入手机--设置，录入至少一个指纹");
                newArray.put("2222");
                newArray.put("3333");
                newArray.put("4444");
                JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);
            }
        } else {
            String CallBackID = pArgs[0];
            JSONArray newArray = new JSONArray();
            newArray.put("硬件不支持");
            newArray.put("2222");
            newArray.put("3333");
            newArray.put("4444");
            JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);
        }

        if ("PluginTestFunction".equals(action)) {
            mFingerprintIdentify.startIdentify(MAX_AVAILABLE_TIMES, new BaseFingerprint.FingerprintIdentifyListener() {
                @Override
                public void onSucceed() {
                    String CallBackID = pArgs[0];
                    JSONArray newArray = new JSONArray();
                    newArray.put("指纹识别成功");
                    newArray.put("2222");
                    newArray.put("3333");
                    newArray.put("4444");
                    JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);
                }

                @Override
                public void onNotMatch(int availableTimes) {
                    if (availableTimes == 0) {
                        String CallBackID = pArgs[0];
                        JSONArray newArray = new JSONArray();
                        newArray.put("指纹验证失败，转密码支付");
                        newArray.put("2222");
                        newArray.put("3333");
                        newArray.put("4444");
                        JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);
                    } else {
                        String CallBackID = pArgs[0];
                        JSONArray newArray = new JSONArray();
                        newArray.put("指纹识别失败");
                        newArray.put("2222");
                        newArray.put("3333");
                        newArray.put("4444");
                        JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);
                    }
                }

                @Override
                public void onFailed(boolean isDeviceLocked) {
                    if (isDeviceLocked) {
                        String CallBackID = pArgs[0];
                        JSONArray newArray = new JSONArray();
                        newArray.put("指纹验证失败，转密码支付");
                        newArray.put("2222");
                        newArray.put("3333");
                        newArray.put("4444");
                        JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);
                    }


                }

                @Override
                public void onStartFailedByDeviceLocked() {
                    String CallBackID = pArgs[0];
                    JSONArray newArray = new JSONArray();
                    newArray.put("指纹验证太过频繁，请稍后重试,转密码支付");
                    newArray.put("2222");
                    newArray.put("3333");
                    newArray.put("4444");
                    JSUtil.execCallback(pWebview, CallBackID, newArray, JSUtil.OK, false);

                }


            });
        }
        return "111";
    }
}
