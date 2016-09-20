package com.study.yizw.customizeview.service;

import com.study.yizw.customizeview.util.CutStringUtil;
import com.study.yizw.customizeview.util.LogUtil;

/**
 * 错误代码及错误代码转换
 * <p>
 * Created by yizw on 2016/9/20 16:45.
 */

public class ErrorCode {

    /**
     * Code定义：
     * 1000-1099：通用字段
     * 1100-1199：xtc服务器，
     * 1200-1299：统一登录系统，
     * 1300-1399：IM服务器
     * 1400-1499：APP内部逻辑处理错误
     * 1500-1599：七牛服务器
     * 1600-1699：网宿云服务器
     * 1700-1799：极光服务器
     * 1800-1899：云吧服务器
     * 1900-1999：高德地图
     * 2000-2099：百度地图
     * 2100-2199：谷歌地图
     * > 9000: 未知错误转换区间
     */
    public interface Code {
        //-------------------------------------common error code -----------------------------------------
        /**
         * 成功
         */
        int SUCCESS = 1000;

        /**
         * 未知错误
         */
        int UNKNOWN_ERROR = 1001;

        /**
         * 解析错误
         */
        int PARSE_ERROR = 1002;

        /**
         * 网络错误
         */
        int NET_WORK_ERROR = 1003;

        /**
         * url未找到
         */
        int URL_NOT_FOUND = 1004;

        /**
         * 连接服务器失败
         */
        int SERVER_DISCONNECTED = 1005;

        /**
         * 服务器内部错误
         */
        int SERVER_INTERNAL_ERROR = 1006;

        /**
         * 请求参数格式错误
         */
        int REQUEST_PARAM_FORMAT_ERROR = 1007;

        /**
         * 数据库错误
         */
        int DATABASE_ERROR = 1008;

        /**
         * 未登录
         */
        int NOT_LOGINED = 1009;

        /**
         * 网络数据错误
         */
        int NET_DATA_ERROR = 1010;

        /**
         * 验证码无效
         */
        int VERIFY_INVALID = 1011;

        /**
         * 没有附带验证码,请附带验证码
         */
        int VERIFY_EMPTY = 1012;

        /**
         * APP版本不支持
         */
        int APP_UNSUPPORT = 1013;

        //-------------------------------------APP error code -----------------------------------------
        /**
         * 手表服务器内部错误
         */
        int APP_SERVER_INNER_ERROR = 1101;

        /**
         * token失效
         */
        int APP_TOKEN_INVALID = 1102;

        /**
         * 签名非法
         */
        int APP_SIGN_INVALID = 1103;
        /**
         * 该资源不存在
         */
        int APP_NO_SUCH_RESOURSE = 1104;

        /**
         * 该资源已存在
         */
        int APP_SUCH_SOURCE_EXISTS = 1105;

        /**
         * 参数错误
         */
        int APP_REQUEST_PARAMETER_INVALID = 1106;

        /**
         * 非法操作
         */
        int APP_ILLEGAL_OPETATION = 1107;

        /**
         * 没有足够的资金
         */
        int APP_NOT_SUFFICIENT_FUNDS = 1108;

        /**
         * 超过手表支持绑定的手机最大个数：一个手表最多绑定20个手机账户
         */
        int APP_OVER_WATCH_MAX_BIND_COUNT = 1109;

        /**
         * 超过手机支持绑定的最大个数：一个手机账户最多绑定5个手表
         */
        int APP_OVER_MOBILE_MAX_BIND_COUNT = 1110;

        /**
         * 绑定联系人最大为50个
         */
        int APP_OVER_CONTACT_MAX_BIND_COUNT = 1111;

        /**
         * 账号被下线
         */
        int APP_OFF_LINE = 1112;

        /**
         * 无运营商
         */
        int APP_NO_OPERATOR = 1113;

        /**
         * 重复添加小伙伴
         */
        int APP_EXIST_FRIEND = 1114;

        /**
         * RSA公钥过期
         */
        int APP_RSA_PUBLIC_KEY_OVERDUE = 1115;

        /**
         * 临时数据错误1
         */
        int APP_TEMP_DATA_ERROR_1 = 1116;

        /**
         * 临时数据错误2
         */
        int APP_TEMP_DATA_ERROR_2 = 1117;

        /**
         * 旧app绑定新版本手表
         */
        int APP_BIND_NEW_WATCH = 1118;

        /**
         * 批量导入通讯录中
         */
        int IN_BATCH_CONTACT = 1119;

        /**
         * 当I3手表切换为自己使用的模式之后，APP访问绑定号接口时，会返回错误码 000045，表示手表当前不允许绑定
         */
        int APP_BIND_DOUBLE_SYSTEM_OWN = 1120;

        /**
         * 暂不支持1-2位的号码
         */
        int APP_NOT_SUPPORT_2_NUMBER = 1121;
        //-------------------------------------SSO error code -----------------------------------------

        /**
         * SSO网络错误
         */
        int SSO_NETWORK_ERROR = 1202;

        /**
         * 验证码错误
         */
        int SSO_RAND_CODE_ERROR = 1203;

        /**
         * 短信或邮箱验证超过了发送次数
         */
        int SSO_RAND_CODE_OVER_LIMIT = 1204;

        /**
         * 用户名为空
         */
        int SSO_NAME_IS_EMPTY = 1205;

        /**
         * 账号已注册
         */
        int SSO_ALREADY_EXIST = 1206;

        /**
         * 用户名不存在
         */
        int SSO_NO_SUCH_NAME_EXISTS = 1207;
        /**
         * 用户名无效
         */
        int SSO_NAME_INVALID = 1208;

        /**
         * 密码为空
         */
        int SSO_PASSWORD_EMPTY = 1209;

        /**
         * 密码不符合规范:密码应该为6~16位字母和数字的组合
         */
        int SSO_PASSWORD_INVALID = 1210;

        /**
         * 旧密码不符合规范
         */
        int SSO_OLD_PASSWORD_INVALID = 1211;

        /**
         * 验证码无效
         */
        int SSO_CHECK_CODE_INVALID = 1212;

        /**
         * 签名无效
         */
        int SSO_SIGN_INVALID = 1213;

        /**
         * 用户名或密码错误
         */
        int SSO_NAME_OR_PASSWORD_ERROR = 1214;

        /**
         * 登录状态无效
         */
        int SSO_TOKEN_INVALID = 1215;

        /**
         * 登录状态超时
         */
        int SSO_TOKEN_TIMEOUT = 1216;

        /**
         * 统一登录系统未知错误
         */
        int SSO_OTHER_ERROR = 1217;

        /**
         * 密码过于简单
         */
        int SSO_PASSWORD_TOO_SIMPLE = 1218;
        /**
         * 今日登录失败次数达到上限
         */
        int SSO_LOGIN_FAILED_MAXIMUM_TODAY = 1219;

        /**
         * 验证码已过期失效
         */
        int SSO_RAND_CODE_EXPIRED = 1220;

        /**
         * 今日输入密码错误次数达到上限
         */
        int SSO_ERROR_PASSWORD_MAXIMUM_TODAY = 1221;

        //-------------------------------------APP内部逻辑处理错误 -----------------------------------------
        /**
         * 下载的apk不是完整的安装包，无法进行安装!
         */
        int APP_ERROR_APK_INCOMPLETE= 1400;
        /**
         * 下载的apk的地址为null，无法进行下载
         */
        int APP_ERROR_APK_DOWN_URL_NULL= 1401;


        //-------------------------------------QINIU error code -----------------------------------------

        /**
         * 文件下载异常
         */
        int QINIU_CONNECT_ERROR = 1501;

        /**
         * 七牛token过期
         */
        int QINIU_TOKEN_OUTDATE = 1502;

        /**
         * 数据为空
         */
        int QINIU_EMPTY_DATA = 1503;
    }


    /**
     * app 服务器错误码转换
     */
    public static class AppConvert {
        public static CodeWapper toCode(String code) {
            CodeWapper codeWapper = new CodeWapper(Code.UNKNOWN_ERROR, CodeWapper.SERVER_TYPE_APP, code);

            LogUtil.d(">>> AppConvert code = " + code);
            if (code == null) {
                return codeWapper;
            }

            int c = Code.UNKNOWN_ERROR;
            switch (code) {
                case "000001":
                    c = Code.SUCCESS;
                    break;
                case "000002":
                    c = Code.APP_SERVER_INNER_ERROR;
                    break;
                case "000003":
                    c = Code.APP_TOKEN_INVALID;
                    break;
                case "000004":
                    c = Code.APP_SIGN_INVALID;
                    break;
                case "000005":
                    c = Code.APP_NO_SUCH_RESOURSE;
                    break;
                case "000006":
                    c = Code.APP_SUCH_SOURCE_EXISTS;
                    break;
                case "000007":
                    c = Code.APP_REQUEST_PARAMETER_INVALID;
                    break;
                case "000008":
                    c = Code.APP_ILLEGAL_OPETATION;
                    break;
                case "000009":
                    c = Code.APP_NOT_SUFFICIENT_FUNDS;
                    break;
                case "000010":
                    c = Code.APP_OVER_WATCH_MAX_BIND_COUNT;
                    break;
                case "000011":
                    c = Code.APP_OVER_MOBILE_MAX_BIND_COUNT;
                    break;
                case "000012":
                    c = Code.APP_OVER_CONTACT_MAX_BIND_COUNT;
                    break;
                case "000013":
                    c = Code.APP_OFF_LINE;
                    break;
                case "000014":
                    c = Code.APP_NO_OPERATOR;
                    break;
                case "000015":
                    c = Code.APP_EXIST_FRIEND;
                    break;
                case "000016":
                    c = Code.APP_RSA_PUBLIC_KEY_OVERDUE;
                    break;
                case "000017":
                    c = Code.APP_TEMP_DATA_ERROR_1;
                    break;
                case "000018":
                    c = Code.APP_TEMP_DATA_ERROR_2;
                    break;
                case "000020":
                    c = Code.VERIFY_INVALID;
                    break;
                case "000022":
                    c = Code.VERIFY_EMPTY;
                    break;
                case "000023":
                    c = Code.APP_OVER_CONTACT_MAX_BIND_COUNT;
                    break;
                case "000033":
                    c = Code.APP_UNSUPPORT;
                    break;
                case "000042":
                    c = Code.APP_BIND_NEW_WATCH;
                    break;
                case "000045":
                    c = Code.APP_BIND_DOUBLE_SYSTEM_OWN;
                    break;
                case "000047":
                    c = Code.APP_NOT_SUPPORT_2_NUMBER;
                    break;
                case "999999":
                    c = Code.IN_BATCH_CONTACT;
                    break;
                default:
                    if (CutStringUtil.isNumeric(code)) {
                        c = 9000 + Integer.parseInt(code);
                    }
                    break;
            }

            codeWapper.code = c;

            return codeWapper;
        }
    }

    /**
     * SSO错误代码转换
     */
    public static class SSOConvert {
        public static CodeWapper toCode(String code) {
            LogUtil.d(">>> SSOConvert code = " + code);

            CodeWapper codeWapper = new CodeWapper(Code.UNKNOWN_ERROR, CodeWapper.SERVER_TYPE_SSO, code);
            if (code == null) {
                return codeWapper;
            }

            int c = Code.UNKNOWN_ERROR;
            switch (code) {
                case "000001":
                    c = Code.SUCCESS;
                    break;
                case "010101":
                    c = Code.SSO_RAND_CODE_ERROR;
                    break;
                case "010102":
                    c = Code.SSO_RAND_CODE_OVER_LIMIT;
                    break;
                case "010105":
                    c = Code.SSO_RAND_CODE_EXPIRED;
                    break;
                case "010201":
                    c = Code.SSO_NAME_IS_EMPTY;
                    break;
                case "010202":
                    c = Code.SSO_ALREADY_EXIST;
                    break;
                case "010203":
                    c = Code.SSO_NO_SUCH_NAME_EXISTS;
                    break;
                case "010204":
                    c = Code.SSO_NAME_INVALID;
                    break;
                case "010301":
                    c = Code.SSO_PASSWORD_EMPTY;
                    break;
                case "010302":
                    c = Code.SSO_PASSWORD_INVALID;
                    break;
                case "010303":
                    c = Code.SSO_OLD_PASSWORD_INVALID;
                    break;
                case "010304":
                    c = Code.SSO_CHECK_CODE_INVALID;
                    break;
                case "010308":
                    c = Code.SSO_PASSWORD_TOO_SIMPLE;
                    break;
                case "010401":
                    c = Code.SSO_SIGN_INVALID;
                    break;
                case "010402":
                    c = Code.SSO_NAME_OR_PASSWORD_ERROR;
                    break;
                case "010403":
                    c = Code.SSO_TOKEN_INVALID;
                    break;
                case "010404":
                    c = Code.SSO_TOKEN_TIMEOUT;
                    break;
                case "010503":
                    c = Code.SSO_LOGIN_FAILED_MAXIMUM_TODAY;
                    break;
                case "010504":
                    c = Code.SSO_ERROR_PASSWORD_MAXIMUM_TODAY;
                    break;
                case "000002":
                    c = Code.SSO_OTHER_ERROR;
                    break;
                default:
                    break;
            }

            codeWapper.code = c;

            return codeWapper;
        }
    }

    /**
     * IM错误代码转换
     */
    public static class IMConvert {

    }

    /**
     * http状态码转换
     */
    public static class StatusConvert {
        public static CodeWapper toCode(int code) {
            LogUtil.d(">>> StatusConvert code = " + code);

            CodeWapper codeWapper = new CodeWapper(Code.UNKNOWN_ERROR, CodeWapper.SERVER_TYPE_COMMON, code + "");

            int c = Code.UNKNOWN_ERROR;
            switch (code) {
                case 200:
                    c = Code.SUCCESS;
                    break;
                case 400:
                    c = Code.REQUEST_PARAM_FORMAT_ERROR;
                    break;
                case 500:
                    c = Code.SERVER_INTERNAL_ERROR;
                    break;
                case 404:
                    c = Code.URL_NOT_FOUND;
                    break;
                case -1:
                    c = Code.NET_WORK_ERROR;
                    break;
                case 0:
                    c = Code.SERVER_DISCONNECTED;
                    break;
                default:
                    break;
            }

            codeWapper.code = c;

            return codeWapper;
        }
    }


}
