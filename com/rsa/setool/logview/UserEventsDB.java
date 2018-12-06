package com.rsa.setool.logview;

import org.json.*;

import java.util.ArrayList;

public class UserEventsDB {
    private static JSONObject allMessages = new JSONObject("{\"2\": [\"notice\", \"user\", \"Authentication\", \"Method now locked\"],\n" +
            "\"3\": [\"notice\", \"user\", \"Authentication\", \"Method unlocked - User successfully authenticated\"],\n" +
            "\"20\": [\"error\", \"user\", \"Authentication\", \"Method enrollment failed - Required parameter missing\"],\n" +
            "\"21\": [\"error\", \"user\", \"Authentication\", \"Method enrollment failed - User does not exist\"],\n" +
            "\"22\": [\"error\", \"user\", \"Authentication\", \"Method enrollment failed - User account inactive\"],\n" +
            "\"23\": [\"error\", \"user\", \"Authentication\", \"Method enrollment failed - Sign-in device not registered to user\"],\n" +
            "\"24\": [\"error\", \"user\", \"Authentication\", \"Method enrollment failed - Provider type not found\"],\n" +
            "\"30\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - Required parameter missing\"],\n" +
            "\"31\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - User does not exist\"],\n" +
            "\"32\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - User account inactive\"],\n" +
            "\"33\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - Application not found\"],\n" +
            "\"34\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - Rule not found\"],\n" +
            "\"35\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - Method locked\"],\n" +
            "\"36\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - Device not registered or authentication method not enrolled\"],\n" +
            "\"51\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - Device not registered\"],\n" +
            "\"52\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - Cannot push notification to device\"],\n" +
            "\"53\": [\"error\", \"user\", \"Authentication\", \"Authentication failed - Internal verification interrupted\"],\n" +
            "\"101\": [\"notice\", \"user\", \"Authentication\", \"Authenticate Tokencode enrollment succeeded\"],\n" +
            "\"102\": [\"error\", \"user\", \"Authentication\", \"Authenticate Tokencode enrollment failed - Authentication device not registered to user\"],\n" +
            "\"103\": [\"notice\", \"user\", \"Authentication\", \"Authenticate Tokencode authentication succeeded\"],\n" +
            "\"104\": [\"error\", \"user\", \"Authentication\", \"Authenticate Tokencode authentication failed - Invalid tokencode\"],\n" +
            "\"105\": [\"error\", \"user\", \"Authentication\", \"Authenticate Tokencode authentication failed - Previously used tokencode detected\"],\n" +
            "\"106\": [\"notice\", \"user\", \"Authentication\", \"Identity router API tokencode request sent to hosted service\"],\n" +
            "\"107\": [\"notice\", \"user\", \"Authentication\", \"Identity router API tokencode response received - Authentication succeeded\"],\n" +
            "\"108\": [\"error\", \"user\", \"Authentication\", \"Identity router API tokencode response received - Authentication failed\"],\n" +
            "\"109\": [\"error\", \"user\", \"Authentication\", \"Identity router API tokencode authentication failed - User not found in identity source\"],\n" +
            "\"110\": [\"error\", \"user\", \"Authentication\", \"Identity router API tokencode authentication failed - Username is associated with multiple user accounts\"],\n" +
            "\"111\": [\"error\", \"user\", \"Authentication\", \"Identity router API tokencode authentication failed - User account disabled in identity source\"],\n" +
            "\"112\": [\"error\", \"user\", \"Authentication\", \"Identity router API tokencode authentication failed - User email address not found in identity source\"],\n" +
            "\"113\": [\"error\", \"user\", \"Authentication\", \"Identity router API tokencode authentication failed - Identity source unreachable\"],\n" +
            "\"114\": [\"error\", \"user\", \"Authentication\", \"Identity router API tokencode authentication failed - Hosted service unreachable\"],\n" +
            "\"115\": [\"error\", \"user\", \"User Status\", \"Identity router API user status check - User not found in identity source\"],\n" +
            "\"116\": [\"error\", \"user\", \"User Status\", \"Identity router API user status check - Username is associated with multiple user accounts\"],\n" +
            "\"117\": [\"error\", \"user\", \"User Status\", \"Identity router API user status check - Identity source unreachable\"],\n" +
            "\"201\": [\"notice\", \"user\", \"Authentication\", \"LDAP password authentication succeeded\"],\n" +
            "\"202\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - Unknown cause\"],\n" +
            "\"203\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - Request timed out or identity router is not connected\"],\n" +
            "\"204\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication provider enrollment failed - Missing email or password\"],\n" +
            "\"205\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication provider enrollment failed - Unknown cause\"],\n" +
            "\"206\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - Provider configuration in the Cloud Authentication Service is incorrect for this user\"],\n" +
            "\"207\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - Provider configuration in the Cloud Authentication Service is incorrect for this user\"],\n" +
            "\"208\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - Missing email or password\"],\n" +
            "\"211\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP server host unreachable. Invalid port or server is not running\"],\n" +
            "\"212\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP server host unresolvable\"],\n" +
            "\"213\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - Cannot establish a trusted SSL connection with the LDAP directory server. Check for invalid certificate\"],\n" +
            "\"215\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - Sign-in failure: unknown username or invalid password\"],\n" +
            "\"216\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP account restriction, for example sign-in time or policy restriction is enforced\"],\n" +
            "\"217\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - Time restriction prevents sign-in for this LDAP account\"],\n" +
            "\"218\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP account not permitted to authenticate through this identity router\"],\n" +
            "\"219\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP password expired\"],\n" +
            "\"220\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP account disabled\"],\n" +
            "\"221\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP account configuration prevents sign-in\"],\n" +
            "\"222\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP account expired\"],\n" +
            "\"223\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP password must be changed using your company's internal procedures\"],\n" +
            "\"224\": [\"error\", \"user\", \"Authentication\", \"LDAP password authentication failed - LDAP account locked out\"],\n" +
            "\"225\": [\"error\", \"user\", \" Authentication  LDAP password authentication failed - LDAP password locked for specified lockout duration\"],\n" +
            "\"300\": [\"notice\", \"user\", \"Authentication\", \"FIDO Token enrollment succeeded\"],\n" +
            "\"301\": [\"error\", \"user\", \"Authentication\", \"FIDO Token enrollment failed - User reached maximum token limit\"],\n" +
            "\"302\": [\"error\", \"user\", \"Authentication\", \"FIDO Token enrollment failed - FIDO protocol error\"],\n" +
            "\"303\": [\"error\", \"user\", \"Authentication\", \"FIDO Token enrollment failed - RSA SecurID Access service error\"],\n" +
            "\"304\": [\"error\", \"user\", \"Authentication\", \"FIDO Token enrollment failed - Unknown error\"],\n" +
            "\"316\": [\"error\", \"user\", \"Authentication\", \"FIDO Token name update failed – Token name cannot be blank\"],\n" +
            "\"317\": [\"error\", \"user\", \"Authentication\", \"FIDO Token name update failed – Token name exceeds 255 characters\"],\n" +
            "\"318\": [\"error\", \"user\", \"Authentication\", \"FIDO Token name update failed – Token name is already in use\"],\n" +
            "\"340\": [\"notice\", \"user\", \"Authentication\", \"FIDO Token authentication succeeded\"],\n" +
            "\"341\": [\"error\", \"user\", \"Authentication\", \"FIDO Token authentication failed - FIDO protocol error\"],\n" +
            "\"342\": [\"error\", \"user\", \"Authentication\", \"FIDO Token authentication failed - RSA SecurID Access service error\"],\n" +
            "\"343\": [\"error\", \"user\", \"Authentication\", \"FIDO Token authentication failed - Unknown error\"],\n" +
            "\"601\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID user authentication succeeded\"],\n" +
            "\"602\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID user authentication succeeded - New PIN accepted\"],\n" +
            "\"603\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID user authentication - Requires new PIN\"],\n" +
            "\"604\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID user authentication - Requires next tokencode\"],\n" +
            "\"605\": [\"error\", \"user\", \"Authentication\", \"RSA SecurID user authentication failed - Invalid passcode\"],\n" +
            "\"606\": [\"error\", \"user\", \"Authentication\", \"RSA SecurID user authentication failed - Invalid next tokencode\"],\n" +
            "\"607\": [\"error\", \"user\", \"Authentication\", \"RSA SecurID user authentication failed - Invalid PIN\"],\n" +
            "\"608\": [\"error\", \"user\", \"Authentication\", \"RSA SecurID user authentication failed - RSA SecurID service is not available\"],\n" +
            "\"609\": [\"error\", \"user\", \"Authentication\", \"RSA SecurID user authentication failed - Unknown cause\"],\n" +
            "\"611\": [\"error\", \"user\", \"Authentication\", \"RSA SecurID user authentication failed - Request timed out\"],\n" +
            "\"701\": [\"notice\", \"user\", \"Authentication\", \"Approve authentication succeeded\"],\n" +
            "\"702\": [\"error\", \"user\", \"Authentication\", \"Approve authentication failed - User response timed out\"],\n" +
            "\"703\": [\"error\", \"user\", \"Authentication\", \"Approve authentication failed - User denied approval\"],\n" +
            "\"704\": [\"error\", \"user\", \"Authentication\", \"Approve enrollment failed\"],\n" +
            "\"707\": [\"notice\", \"user\", \"Authentication\", \"Approve enrollment succeeded\"],\n" +
            "\"709\": [\"error\", \"user\", \" Authentication  Approve authentication failed - All in-progress authentication requests canceled\"],\n" +
            "\"801\": [\"notice\", \"user\", \"Authentication\", \"Device Biometrics authentication succeeded\"],\n" +
            "\"802\": [\"error\", \"user\", \"Authentication\", \"Device Biometrics authentication failed - User response timed out\"],\n" +
            "\"803\": [\"error\", \"user\", \"Authentication\", \"Fingerprint Verification authentication failed - User fingerprint verification failed\"],\n" +
            "\"804\": [\"error\", \"user\", \"Authentication\", \"RSA SecurID Access enrollment for Fingerprint Verification failed\"],\n" +
            "\"805\": [\"error\", \"user\", \"Authentication\", \"Fingerprint Verification authentication failed - Unexpected error\"],\n" +
            "\"806\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID Access enrollment for Fingerprint Verification succeeded\"],\n" +
            "\"807\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID Access unenrollment for Fingerprint Verification succeeded - Device unenrolled\"],\n" +
            "\"901\": [\"notice\", \"user\", \"Authentication\", \"Portal sign-in succeeded\"],\n" +
            "\"902\": [\"error\", \"user\", \"Authentication\", \"Portal sign-in failed - Authentication failed\"],\n" +
            "\"903\": [\"error\", \"user\", \"Authentication\", \"Portal sign-in failed - Credentials are associated with multiple user accounts\"],\n" +
            "\"904\": [\"error\", \"user\", \"Authentication\", \"Portal sign-in failed - Internal server error\"],\n" +
            "\"905\": [\"error\", \"user\", \"Authentication\", \"Portal sign-in failed - Concurrent session limit reached\"],\n" +
            "\"906\": [\"error\", \"user\", \"Authentication\", \"Portal sign-in failed - Password reset required\"],\n" +
            "\"907\": [\"notice\", \"user\", \"Authentication\", \"Portal sign-out succeeded\"],\n" +
            "\"908\": [\"notice\", \"user\", \"Authentication\", \"Protected application authentication attempt made\"],\n" +
            "\"909\": [\"notice\", \"user\", \"Authentication\", \"Protected application authentication succeeded\"],\n" +
            "\"910\": [\"error\", \"user\", \"Authentication\", \"Protected application authentication failed\"],\n" +
            "\"911\": [\"notice\", \"user\", \"Authentication\", \"Additional authentication initiated\"],\n" +
            "\"912\": [\"notice\", \"user\", \"Authentication\", \"Additional authentication succeeded\"],\n" +
            "\"913\": [\"error\", \"user\", \"Authentication\", \"Additional authentication failed\"],\n" +
            "\"931\": [\"notice\", \"user\", \"Authentication\", \"Additional authentication is not needed because the user already authenticated at the same assurance level or higher\"],\n" +
            "\"932\": [\"error\", \"user\", \"Authentication\", \"Additional authentication failed - User account inactive\"],\n" +
            "\"933\": [\"error\", \"user\", \"Authentication\", \"Password authentication succeeded - Client does not support required additional authentication methods - Access denied\"],\n" +
            "\"934\": [\"notice\", \"user\", \"Authentication\", \"Password authentication succeeded\"],\n" +
            "\"935\": [\"error\", \"user\", \"Authentication\", \"Unsuccessful password authentication – Access denied\"],\n" +
            "\"936\": [\"error\", \"user\", \"Authentication\", \"Unsuccessful password authentication - Credentials are associated with multiple user accounts\"],\n" +
            "\"937\": [\"error\", \"user\", \"Authentication\", \"Unsuccessful password authentication - Internal server error\"],\n" +
            "\"938\": [\"error\", \"user\", \"Authentication\", \"Unsuccessful password authentication - Concurrent session limit reached\"],\n" +
            "\"939\": [\"notice\", \"user\", \"Authorization\", \"Password authentication succeeded - Policy does not require additional authentication - Access granted\"],\n" +
            "\"940\": [\"error\", \"user\", \"Authorization\", \"Password authentication succeeded - User prohibited by policy settings - Access denied\"],\n" +
            "\"941\": [\"error\", \"user\", \"Authorization\", \"Password authentication succeeded - Access prohibited by conditional policy settings - Access denied\"],\n" +
            "\"3000\": [\"notice\", \"user\", \"Device Management\", \"Device registration succeeded\"],\n" +
            "\"3001\": [\"error\", \"user\", \"Device Management\", \"Device registration failed\"],\n" +
            "\"3002\": [\"error\", \"user\", \"Device Management\", \"Device registration unsuccessful. Maximum limit (1) for devices reached\"],\n" +
            "\"5104\": [\"error\", \"user\", \"Authentication\", \"Cloud Administration Console logon failed - User account inactive\"],\n" +
            "\"5107\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID Access admin password changed\"],\n" +
            "\"20201\": [\"notice\", \"user\", \"Authentication\", \"Eyeprint Verification authentication succeeded\"],\n" +
            "\"20202\": [\"error\", \"user\", \"Authentication\", \"Eyeprint Verification authentication failed - User response timed out\"],\n" +
            "\"20203\": [\"error\", \"user\", \"Authentication\", \"Eyeprint Verification authentication failed - User Eyeprint verification failed\"],\n" +
            "\"20204\": [\"error\", \"user\", \"Authentication\", \"RSA SecurID Access enrollment for Eyeprint Verification failed\"],\n" +
            "\"20205\": [\"error\", \"user\", \"Authentication\", \"Eyeprint Verification authentication failed - Unexpected error\"],\n" +
            "\"20206\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID Access enrollment for Eyeprint ID succeeded\"],\n" +
            "\"20207\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID Access unenrollment for Eyeprint ID succeeded\"],\n" +
            "\"20208\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID Access rule-retrieved for Eyeprint\"],\n" +
            "\"20209\": [\"notice\", \"user\", \"Authentication\", \"RSA SecurID Access unenrollment for Eyeprint failed\"],\n" +
            "\"20301\": [\"notice\", \"user\", \"Authentication\", \"Multifactor authentication initiated\"],\n" +
            "\"20302\": [\"notice\", \"user\", \"Authentication\", \"Multifactor authentication succeeded\"],\n" +
            "\"20303\": [\"error\", \"user\", \"Authentication\", \"Multifactor authentication was unsuccessful\"],\n" +
            "\"20400\": [\"notice\", \"user\", \"Authentication\", \"SAML IdP - Authentication request received\"],\n" +
            "\"20401\": [\"notice\", \"user\", \"Authentication\", \"SAML IdP - Assertion sent for successful user authentication\"],\n" +
            "\"20402\": [\"error\", \"user\", \"Authentication\", \"SAML IdP - Response sent for unsuccessful user authentication\"],\n" +
            "\"20403\": [\"error\", \"user\", \"Authentication\", \"SAML IdP - Error response sent\"],\n" +
            "\"20601\": [\"error\", \"user\", \"Authentication\", \"RADIUS - LDAP authentication succeeded - Policy contains no RADIUS-compatible methods for additional authentication - Access denied\"],\n" +
            "\"20602\": [\"error\", \"user\", \"Authentication\", \"RADIUS - LDAP authentication succeeded - No user device registered for required additional authentication methods - Access denied\"],\n" +
            "\"20603\": [\"error\", \"user\", \"Authentication\", \"RADIUS - Invalid format for additional authentication request - Access denied\"],\n" +
            "\"20604\": [\"error\", \"user\", \"Authentication\", \"RADIUS - Invalid checklist attributes - Access denied\"],\n" +
            "\"20605\": [\"error\", \"user\", \"Authentication\", \"RADIUS - Hosted Authentication Service unreachable - Access denied\"],\n" +
            "\"20606\": [\"error\", \"user\", \"Authentication\", \"RADIUS – Approve authentication failed – Method timeout\"],\n" +
            "\"20607\": [\"error\", \"user\", \"Authentication\", \"RADIUS - Eyeprint ID authentication failed - Method timeout\"],\n" +
            "\"20608\": [\"error\", \"user\", \"Authentication\", \"RADIUS - Fingerprint authentication failed - Method timeout\"],\n" +
            "\"20609\": [\"error\", \"user\", \"Authentication\", \"RADIUS - Authentication failed - Internal error\"],\n" +
            "\"20701\": [\"error\", \"user\", \"Authentication\", \"Access denied – User not a member of any identity source in access policy\"],\n" +
            "\"20702\": [\"error\", \"user\", \"Authentication\", \"Access denied – User does not match rule set in access policy\"],\n" +
            "\"20703\": [\"error\", \"user\", \"Authentication\", \"Access denied – Policy authentication conditions deny access\"],\n" +
            "\"20801\": [\"error\", \"user\", \"Authentication\", \"SMS Tokencode message transmission attempted\"],\n" +
            "\"20802\": [\"error\", \"user\", \"Authentication\", \"SMS Tokencode message transmission attempt failed - Invalid phone number\"],\n" +
            "\"20803\": [\"error\", \"user\", \"Authentication\", \"SMS Tokencode message transmission attempt failed\"],\n" +
            "\"20804\": [\"error\", \"user\", \"Authentication\", \"SMS Tokencode regenerated\"],\n" +
            "\"20805\": [\"error\", \"user\", \"Authentication\", \"SMS Tokencode delivery failed\"],\n" +
            "\"20851\": [\"notice\", \"user\", \"Authentication\", \"Voice Tokencode call succeeded\"],\n" +
            "\"20852\": [\"error\", \"user\", \"Authentication\", \"Voice Tokencode call attempt failed - Invalid phone number\"],\n" +
            "\"20853\": [\"error\", \"user\", \"Authentication\", \"Voice Tokencode call attempt failed\"],\n" +
            "\"20854\": [\"error\", \"user\", \"Authentication\", \"Voice Tokencode regenerated\"],\n" +
            "\"20855\": [\"error\", \"user\", \"Authentication\", \"Voice Tokencode delivery failed\"],\n" +
            "\"20900\": [\"notice\", \"user\", \"Authentication\", \"OIDC - Authentication request received\"],\n" +
            "\"20901\": [\"notice\", \"user\", \"Authentication\", \"OIDC - ID Token sent for successful user authentication\"],\n" +
            "\"20902\": [\"error\", \"user\", \"Authentication\",  \"OIDC - Response sent for unsuccessful user authentication\"],\n" +
            "\"20903\": [\"error\", \"user\", \"Authentication\",  \"OIDC - Error response sent\"],\n" +
            "\"21901\": [\"notice\", \"user\", \"Authentication\", \"SMS Tokencode verification succeeded\"],\n" +
            "\"21902\": [\"error\", \"user\", \"Authentication\", \"SMS Tokencode verification failed\"],\n" +
            "\"21903\": [\"error\", \"user\", \"Authentication\", \"SMS Tokencode authentication method locked – User exceeded maximum tokencodes allowed\"],\n" +
            "\"21953\": [\"error\", \"user\", \"Authentication\", \"Voice Tokencode authentication method locked - User exceeded maximum tokencodes allowed.\"]\n" +
            "}");

    public static ArrayList<Integer> getAllErrors() {
        ArrayList<Integer> allError = new ArrayList<>();
        for (String key : allMessages.keySet()) {
            if (((JSONArray) allMessages.get(key)).get(0).equals("error")) {
                allError.add(Integer.parseInt(key));
            }
        }
        return allError;
    }

    public static ArrayList<Integer> getAllNotices() {
        ArrayList<Integer> allNotices = new ArrayList<>();
        for (String key : allMessages.keySet()) {
            if (((JSONArray) allMessages.get(key)).get(0).equals("notice")) {
                allNotices.add(Integer.parseInt(key));
            }
        }
        return allNotices;
    }

    public static String getEventDescreption(int eventCode) {
        try {
            JSONArray ss = (JSONArray) allMessages.get(Integer.toString(eventCode));
            return (String) (ss).get(3);
        }catch (org.json.JSONException ex){
            return "Unknown error";
        }
    }
}
