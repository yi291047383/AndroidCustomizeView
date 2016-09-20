package com.study.yizw.customizeview.service;

import android.content.Context;

import com.study.yizw.customizeview.R;
import com.study.yizw.customizeview.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常错误描叙类
 * <p>
 * Created by yizw on 2016/9/20 16:44.
 */

public class ErrorCodeDescribe {

    private static Map<String, Integer> tips = new HashMap<>();

    public static String DEFAULT = "default";

    static {
        // 成功相关提示语
        tips.put(ErrorCode.Code.SUCCESS + DEFAULT, R.string.code_success);

        tips.put(ErrorCode.Code.UNKNOWN_ERROR + DEFAULT, R.string.code_unkonwn_error);
        tips.put(ErrorCode.Code.PARSE_ERROR + DEFAULT, R.string.code_parse_error);
        tips.put(ErrorCode.Code.NET_WORK_ERROR + DEFAULT, R.string.code_net_work_error);
        tips.put(ErrorCode.Code.URL_NOT_FOUND + DEFAULT, R.string.code_url_not_found);
        tips.put(ErrorCode.Code.SERVER_DISCONNECTED + DEFAULT, R.string.code_server_disconnected);
        tips.put(ErrorCode.Code.SERVER_INTERNAL_ERROR + DEFAULT, R.string.code_server_internal_error);
        tips.put(ErrorCode.Code.REQUEST_PARAM_FORMAT_ERROR + DEFAULT, R.string.code_request_param_format_error);
        tips.put(ErrorCode.Code.DATABASE_ERROR + DEFAULT, R.string.code_database_error);
        tips.put(ErrorCode.Code.NOT_LOGINED + DEFAULT, R.string.code_not_logined);
        tips.put(ErrorCode.Code.NET_DATA_ERROR + DEFAULT, R.string.code_net_data_error);



        tips.put(ErrorCode.Code.APP_SERVER_INNER_ERROR + DEFAULT, R.string.code_app_server_inner_error);
        tips.put(ErrorCode.Code.APP_TOKEN_INVALID + DEFAULT, R.string.code_app_token_invaild);
        tips.put(ErrorCode.Code.APP_SIGN_INVALID + DEFAULT, R.string.code_app_sign_invalid);
        tips.put(ErrorCode.Code.APP_NO_SUCH_RESOURSE + DEFAULT, R.string.code_app_no_such_resourse);
        tips.put(ErrorCode.Code.APP_SUCH_SOURCE_EXISTS + DEFAULT, R.string.code_app_such_source_exists);

        tips.put(ErrorCode.Code.APP_SUCH_SOURCE_EXISTS + DEFAULT, R.string.code_app_such_source_exists);
        tips.put(ErrorCode.Code.APP_REQUEST_PARAMETER_INVALID + DEFAULT, R.string.code_app_request_parameter_invalid);
        tips.put(ErrorCode.Code.APP_ILLEGAL_OPETATION + DEFAULT, R.string.code_app_illegal_opetation);
        tips.put(ErrorCode.Code.APP_NOT_SUFFICIENT_FUNDS + DEFAULT, R.string.code_app_not_sufficient_funds);
        tips.put(ErrorCode.Code.APP_OVER_WATCH_MAX_BIND_COUNT + DEFAULT, R.string.code_app_over_watch_max_bind_count);

        tips.put(ErrorCode.Code.APP_OVER_MOBILE_MAX_BIND_COUNT + DEFAULT, R.string.code_app_over_mobile_max_bind_count);
        tips.put(ErrorCode.Code.APP_OVER_CONTACT_MAX_BIND_COUNT + DEFAULT, R.string.code_app_over_contact_max_bind_count);
        tips.put(ErrorCode.Code.APP_OFF_LINE + DEFAULT, R.string.code_app_off_line);
        tips.put(ErrorCode.Code.APP_NO_OPERATOR + DEFAULT, R.string.code_app_no_operator);

        tips.put(ErrorCode.Code.APP_EXIST_FRIEND + DEFAULT, R.string.code_app_exist_friend);
        tips.put(ErrorCode.Code.APP_RSA_PUBLIC_KEY_OVERDUE + DEFAULT, R.string.code_app_rsa_public_key_overdue);
        tips.put(ErrorCode.Code.APP_TEMP_DATA_ERROR_1 + DEFAULT, R.string.code_app_data_error);
        tips.put(ErrorCode.Code.APP_TEMP_DATA_ERROR_2 + DEFAULT, R.string.code_app_data_error);



        tips.put(ErrorCode.Code.SSO_NETWORK_ERROR + DEFAULT, R.string.code_sso_network_error);
        tips.put(ErrorCode.Code.SSO_RAND_CODE_ERROR + DEFAULT, R.string.code_sso_rand_code_error);
        tips.put(ErrorCode.Code.SSO_RAND_CODE_OVER_LIMIT + DEFAULT, R.string.code_sso_rand_code_over_limit);
        tips.put(ErrorCode.Code.SSO_NAME_IS_EMPTY + DEFAULT, R.string.code_sso_name_is_empty);
        tips.put(ErrorCode.Code.SSO_ALREADY_EXIST + DEFAULT, R.string.code_sso_already_exist);

        tips.put(ErrorCode.Code.SSO_NO_SUCH_NAME_EXISTS + DEFAULT, R.string.code_sso_no_such_name_exists);
        tips.put(ErrorCode.Code.SSO_NAME_INVALID + DEFAULT, R.string.code_sso_name_invalid);
        tips.put(ErrorCode.Code.SSO_PASSWORD_EMPTY + DEFAULT, R.string.code_sso_password_empty);
        tips.put(ErrorCode.Code.SSO_PASSWORD_INVALID + DEFAULT, R.string.code_sso_password_invalid);
        tips.put(ErrorCode.Code.SSO_OLD_PASSWORD_INVALID + DEFAULT, R.string.code_sso_old_password_invalid);

        tips.put(ErrorCode.Code.SSO_CHECK_CODE_INVALID + DEFAULT, R.string.code_sso_check_code_invaild);
        tips.put(ErrorCode.Code.SSO_SIGN_INVALID + DEFAULT, R.string.code_sso_sign_invalid);
        tips.put(ErrorCode.Code.SSO_NAME_OR_PASSWORD_ERROR + DEFAULT, R.string.code_sso_name_or_password_error);
        tips.put(ErrorCode.Code.SSO_LOGIN_FAILED_MAXIMUM_TODAY + DEFAULT, R.string.sso_password_invalid_count_0);
        tips.put(ErrorCode.Code.SSO_TOKEN_INVALID + DEFAULT, R.string.code_sso_token_invalid);
        tips.put(ErrorCode.Code.SSO_TOKEN_TIMEOUT + DEFAULT, R.string.code_sso_token_timeout);

        tips.put(ErrorCode.Code.SSO_OTHER_ERROR + DEFAULT, R.string.code_sso_other_error);



        tips.put(ErrorCode.Code.QINIU_CONNECT_ERROR + DEFAULT, R.string.code_qiniu_connect_error);
        tips.put(ErrorCode.Code.QINIU_TOKEN_OUTDATE + DEFAULT, R.string.code_qiniu_token_outdate);
        tips.put(ErrorCode.Code.QINIU_EMPTY_DATA + DEFAULT, R.string.code_qiniu_empty_data);

    }



    /**
     * 获取code对应的文字描述
     * @param context
     * @param codeWapper
     * @return
     */
    public static String getDescribe(Context context, CodeWapper codeWapper) {
        return getDescribe(context, DEFAULT, codeWapper);
    }

    /**
     * 获取code对应的文字描述
     * @param codeWapper
     * @return
     */
    public static String getDescribe(Context context, String tag, CodeWapper codeWapper) {
        if (tag == null || "".equals(tag)) {
            tag = DEFAULT;
        }

        if (codeWapper == null) {
            codeWapper = new CodeWapper();
        }

        if (context == null) {
            LogUtil.e("context is null.");
            return "";
        }

        String describe = context.getString(R.string.code_undisposed);

        Integer resId = tips.get(codeWapper.code + tag);
        if (resId != null) {
            describe = context.getString(resId);
        } else {
            LogUtil.e("convertSuccess error, context:" + context + ", tag:" + tag + ", codeWapper:" + codeWapper);
        }

        if ("default".equals(tag) && codeWapper != null) {
            describe += codeWapper.code;
        }

        return describe;
    }




}
