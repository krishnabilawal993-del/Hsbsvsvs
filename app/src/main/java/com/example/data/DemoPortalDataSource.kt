package com.example.data

import com.example.model.AdmissionStream
import com.example.model.ChecklistDocument
import com.example.model.FormHelpGuide
import com.example.model.FormStep
import com.example.model.LocalizedText
import com.example.model.PortalCategory
import com.example.model.PortalUpdateItem
import com.example.model.QuickPortalLink
import com.example.model.ScholarshipCategory
import com.example.model.SchemeScope

/**
 * Curated DEMO dataset for Gujarat Sahayak.
 *
 * Transparency policy:
 * - Zero fabricated vacancy counts or invented exam deadlines.
 * - Every entry is explicitly flagged with `isDemo = true` and links directly to real,
 *   verified official Gujarat & Central Government web portals.
 */
object DemoPortalDataSource {

    val quickPortals: List<QuickPortalLink> = listOf(
        QuickPortalLink(
            id = "qp_ojas",
            name = LocalizedText("OJAS ગુજરાત", "OJAS गुजरात"),
            domain = "ojas.gujarat.gov.in",
            url = "https://ojas.gujarat.gov.in",
            tag = LocalizedText("સરકારી ભરતી", "सरकारी भर्ती")
        ),
        QuickPortalLink(
            id = "qp_digital_gujarat",
            name = LocalizedText("ડિજિટલ ગુજરાત", "डिजिटल गुजरात"),
            domain = "digitalgujarat.gov.in",
            url = "https://www.digitalgujarat.gov.in",
            tag = LocalizedText("સ્કોલરશિપ", "स्कॉलरशिप")
        ),
        QuickPortalLink(
            id = "qp_gcas",
            name = LocalizedText("GCAS પોર્ટલ", "GCAS पोर्टल"),
            domain = "gcas.gujgov.edu.in",
            url = "https://gcas.gujgov.edu.in",
            tag = LocalizedText("BA/BCom/BSc", "BA/BCom/BSc")
        ),
        QuickPortalLink(
            id = "qp_iti",
            name = LocalizedText("ગુજરાત ITI", "गुजरात ITI"),
            domain = "itiadmission.gujarat.gov.in",
            url = "https://itiadmission.gujarat.gov.in",
            tag = LocalizedText("ITI એડમિશન", "ITI एडमिशन")
        ),
        QuickPortalLink(
            id = "qp_anubandham",
            name = LocalizedText("અનુબંધમ", "अनुबंधम"),
            domain = "anubandham.gujarat.gov.in",
            url = "https://anubandham.gujarat.gov.in",
            tag = LocalizedText("પ્રાઇવેટ નોકરી", "प्राइवेट नौकरी")
        ),
        QuickPortalLink(
            id = "qp_ikhedut",
            name = LocalizedText("આઈ-ખેડૂત", "आई-खेडूत"),
            domain = "ikhedut.gujarat.gov.in",
            url = "https://ikhedut.gujarat.gov.in",
            tag = LocalizedText("ખેડૂત યોજના", "किसान योजना")
        ),
        QuickPortalLink(
            id = "qp_esamajkalyan",
            name = LocalizedText("ઈ-સમાજ કલ્યાણ", "ई-समाज कल्याण"),
            domain = "esamajkalyan.gujarat.gov.in",
            url = "https://esamajkalyan.gujarat.gov.in",
            tag = LocalizedText("કલ્યાણ યોજના", "कल्याण योजना")
        )
    )

    val demoItems: List<PortalUpdateItem> = listOf(
        // ==========================================
        // 1. GOVERNMENT JOBS (Official Gujarat Portals — Demo Reference Entries)
        // ==========================================
        PortalUpdateItem(
            id = "job_govt_ojas",
            category = PortalCategory.GOVT_JOBS,
            title = LocalizedText(
                gu = "OJAS ગુજરાત ભરતી પોર્ટલ (GSSSB / GPSSB / પોલીસ) — ડેમો માર્ગદર્શિકા",
                hi = "OJAS गुजरात भर्ती पोर्टल (GSSSB / GPSSB / पुलिस) — डेमो गाइड"
            ),
            organization = LocalizedText(
                gu = "ઓનલાઈન જોબ એપ્લિકેશન સિસ્ટમ (OJAS), ગુજરાત સરકાર",
                hi = "ऑनलाइन जॉब एप्लीकेशन सिस्टम (OJAS), गुजरात सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "ગુજરાત ગૌણ સેવા પસંદગી મંડળ (GSSSB), પંચાયત સેવા (GPSSB) અને અન્ય રાજ્ય સરકારની ભરતીઓની સત્તાવાર જાહેરાત, કોલ લેટર અને અરજી માટેનું એકમાત્ર પોર્ટલ.",
                hi = "गुजरात गौण सेवा पसंदगी मंडल (GSSSB), पंचायत सेवा (GPSSB) और राज्य सरकार की भर्तियों की आधिकारिक अधिसूचना, कॉल लेटर और आवेदन का मुख्य पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "વિવિધ પોસ્ટ મુજબ ધોરણ 10 / 12 / સ્નાતક (Graduate) અને કોમ્પ્યુટર જ્ઞાન (CCC). દરેક જાહેરાતની સત્તાવાર PDF નોટિફિકેશન OJAS પર ચકાસો.",
                hi = "विभिन्न पदों के अनुसार 10वीं / 12वीं / स्नातक (Graduate) और कंप्यूटर ज्ञान (CCC)। प्रत्येक भर्ती की आधिकारिक PDF अधिसूचना OJAS पर जांचें।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — તાજી અરજી અને અંતિમ તારીખો OJAS ના 'Current Advertisement' વિભાગમાં ચકાસો.",
                hi = "डेमो प्रविष्टि — नवीनतम आवेदन और अंतिम तिथियां OJAS के 'Current Advertisement' सेक्शन में जांचें।"
            ),
            publishedDate = "2026-11-20",
            officialSourceName = "OJAS Gujarat (ojas.gujarat.gov.in)",
            officialUrl = "https://ojas.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("OJAS", "GSSSB", "GPSSB", "Class 3", "Gujarat Govt"),
            requiredDocuments = listOf(
                LocalizedText("પાસપોર્ટ સાઇઝ ફોટો અને સહી (સ્કેન કરેલ)", "पासपोर्ट साइज़ फोटो और हस्ताक्षर (स्कैन किया हुआ)"),
                LocalizedText("ધોરણ 10 / 12 / સ્નાતકની માર્કશીટ", "10वीं / 12वीं / स्नातक की मार्कशीट"),
                LocalizedText("જાતિનું પ્રમાણપત્ર / નોન-ક્રિમીલેયર / EWS (લાગુ પડતું હોય તો)", "जाति प्रमाण-पत्र / नॉन-क्रीमीलेयर / EWS (यदि लागू हो)"),
                LocalizedText("મોબાઇલ નંબર અને ઇમેઇલ આઇડી (OTR માટે)", "मोबाइल नंबर और ईमेल आईडी (OTR के लिए)")
            ),
            howToApplySteps = listOf(
                LocalizedText("ojas.gujarat.gov.in ખોલો અને 'One Time Registration (OTR)' પૂર્ણ કરો.", "ojas.gujarat.gov.in खोलें और 'One Time Registration (OTR)' पूरा करें।"),
                LocalizedText("Online Application -> Apply માં જઈ ચાલુ જાહેરાત પસંદ કરો.", "Online Application -> Apply में जाकर वर्तमान विज्ञापन चुनें।"),
                LocalizedText("વિગતો ભરી ફોટો/સહી અપલોડ કરો અને અરજી Confirm કરી પ્રિન્ટ સાચવો.", "विवरण भरकर फोटो/हस्ताक्षर अपलोड करें और आवेदन Confirm कर प्रिंट सेव करें।")
            ),
            isDemo = true
        ),
        PortalUpdateItem(
            id = "job_govt_gpsc",
            category = PortalCategory.GOVT_JOBS,
            title = LocalizedText(
                gu = "GPSC વર્ગ-1, 2 અને તકનીકી ભરતી પોર્ટલ — ડેમો સંદર્ભ",
                hi = "GPSC वर्ग-1, 2 और तकनीकी भर्ती पोर्टल — डेमो संदर्भ"
            ),
            organization = LocalizedText(
                gu = "ગુજરાત જાહેર સેવા આયોગ (GPSC), ગાંધીનગર",
                hi = "गुजरात लोक सेवा आयोग (GPSC), गांधीनगर"
            ),
            shortSummary = LocalizedText(
                gu = "ગુજરાત વહીવટી સેવા (GAS), DySP, મામલતદાર, એન્જિનિયર અને અન્ય વર્ગ-1 અને વર્ગ-2 પરીક્ષાઓનું વાર્ષિક કેલેન્ડર અને સત્તાવાર જાહેરાતો.",
                hi = "गुजरात प्रशासनिक सेवा (GAS), DySP, मामलतदार, इंजीनियर तथा अन्य वर्ग-1 और वर्ग-2 परीक्षाओं का वार्षिक कैलेंडर और आधिकारिक विज्ञापन।"
            ),
            eligibility = LocalizedText(
                gu = "માન્ય યુનિવર્સિટીમાંથી સ્નાતક (Graduation) અથવા પોસ્ટ મુજબ વ્યાવસાયિક ડિગ્રી. વય મર્યાદા GPSC નિયમો મુજબ.",
                hi = "मान्यता प्राप्त विश्वविद्यालय से स्नातक (Graduation) या पद अनुसार पेशेवर डिग्री। आयु सीमा GPSC नियमों के अनुसार।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — GPSC નું લાઇવ પરીક્ષા કેલેન્ડર અને અરજી તારીખો gpsc.gujarat.gov.in પર ઉપલબ્ધ છે.",
                hi = "डेमो प्रविष्टि — GPSC का लाइव परीक्षा कैलेंडर और आवेदन तिथियां gpsc.gujarat.gov.in पर उपलब्ध हैं।"
            ),
            publishedDate = "2026-11-19",
            officialSourceName = "GPSC Official (gpsc.gujarat.gov.in)",
            officialUrl = "https://gpsc.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("GPSC", "Class 1-2", "Civil Services", "Exam Calendar"),
            requiredDocuments = listOf(
                LocalizedText("સ્નાતક ડિગ્રી પ્રમાણપત્ર અને અંતિમ વર્ષની માર્કશીટ", "स्नातक डिग्री प्रमाण-पत्र और अंतिम वर्ष की मार्कशीट"),
                LocalizedText("ઓળખ પત્ર (આધાર / પાન / ચૂંટણી કાર્ડ)", "पहचान पत्र (आधार / पैन / वोटर आईडी)"),
                LocalizedText("અનામત કેટેગરી પ્રમાણપત્ર (લાગુ પડતું હોય તો)", "आरक्षण श्रेणी प्रमाण-पत्र (यदि लागू हो)")
            ),
            howToApplySteps = listOf(
                LocalizedText("gpsc.gujarat.gov.in પર ચાલુ જાહેરાત અને અભ્યાસક્રમ વાંચો.", "gpsc.gujarat.gov.in पर वर्तमान अधिसूचना और सिलेबस पढ़ें।"),
                LocalizedText("gpsc-ojas.gujarat.gov.in દ્વારા ઓનલાઈન અરજી અને ફી ભરો.", "gpsc-ojas.gujarat.gov.in के माध्यम से ऑनलाइन आवेदन और शुल्क जमा करें।"),
                LocalizedText("અરજીનો કન્ફર્મેશન નંબર અને ફી રસીદ સાચવી રાખો.", "आवेदन का कन्फर्मेशन नंबर और शुल्क रसीद सुरक्षित रखें।")
            ),
            isDemo = true
        ),
        PortalUpdateItem(
            id = "job_govt_police",
            category = PortalCategory.GOVT_JOBS,
            title = LocalizedText(
                gu = "ગુજરાત પોલીસ ભરતી બોર્ડ (LRD / PSI) — સત્તાવાર અપડેટ પોર્ટલ (ડેમો)",
                hi = "गुजरात पुलिस भर्ती बोर्ड (LRD / PSI) — आधिकारिक अपडेट पोर्टल (डेमो)"
            ),
            organization = LocalizedText(
                gu = "ગુજરાત પોલીસ ભરતી બોર્ડ (GPRB), ગાંધીનગર",
                hi = "गुजरात पुलिस भर्ती बोर्ड (GPRB), गांधीनगर"
            ),
            shortSummary = LocalizedText(
                gu = "લોકરક્ષક દળ (LRD કોન્સ્ટેબલ) અને પોલીસ સબ ઇન્સ્પેક્ટર (PSI) ની શારીરિક કસોટી, લેખિત પરીક્ષા નિયમો અને સત્તાવાર સૂચનાઓ.",
                hi = "लोकरक्षक दल (LRD कांस्टेबल) और पुलिस सब इंस्पेक्टर (PSI) की शारीरिक परीक्षा, लिखित परीक्षा नियम और आधिकारिक सूचनाएं।"
            ),
            eligibility = LocalizedText(
                gu = "કોન્સ્ટેબલ માટે ધોરણ 12 પાસ અને PSI માટે સ્નાતક (સત્તાવાર ભરતી નિયમો અને શારીરિક ધોરણો પોર્ટલ પર ચકાસો).",
                hi = "कांस्टेबल के लिए 12वीं पास और PSI के लिए स्नातक (आधिकारिक भर्ती नियम और शारीरिक मानदंड पोर्टल पर जांचें)।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — તાજી ભરતી જાહેરાત અને પરિણામ gprb.gujarat.gov.in પર જુઓ.",
                hi = "डेमो प्रविष्टि — नवीनतम भर्ती सूचना और परिणाम gprb.gujarat.gov.in पर देखें।"
            ),
            publishedDate = "2026-11-18",
            officialSourceName = "GPRB Gujarat (gprb.gujarat.gov.in)",
            officialUrl = "https://gprb.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("Police", "LRD", "PSI", "GPRB", "12th Pass"),
            requiredDocuments = listOf(
                LocalizedText("ધોરણ 12 અથવા સ્નાતક પ્રમાણપત્ર", "12वीं या स्नातक प्रमाण-पत्र"),
                LocalizedText("જન્મ તારીખનો પુરાવો (LC / Birth Certificate)", "जन्म तिथि प्रमाण (LC / जन्म प्रमाण-पत्र)"),
                LocalizedText("NCC 'C' અથવા રમતગમત પ્રમાણપત્ર (હોય તો)", "NCC 'C' या खेल प्रमाण-पत्र (यदि हो)")
            ),
            howToApplySteps = listOf(
                LocalizedText("gprb.gujarat.gov.in પર નવીનતમ ભરતી નિયમો અને નોટિસ વાંચો.", "gprb.gujarat.gov.in पर नवीनतम भर्ती नियम और नोटिस पढ़ें।"),
                LocalizedText("અધિકૃત જાહેરાત ખૂલે ત્યારે OJAS પોર્ટલ પરથી ઓનલાઈન અરજી કરો.", "आधिकारिक विज्ञापन खुलने पर OJAS पोर्टल से ऑनलाइन आवेदन करें।")
            ),
            isDemo = true
        ),

        // ==========================================
        // 2. PRIVATE JOBS (Official Govt-Verified Employment Portals — Demo Reference Entries)
        // ==========================================
        PortalUpdateItem(
            id = "job_priv_anubandham",
            category = PortalCategory.PRIVATE_JOBS,
            title = LocalizedText(
                gu = "અનુબંધમ ગુજરાત — ખાનગી ક્ષેત્ર રોજગાર અને ભરતી મેળા (ડેમો)",
                hi = "अनुबंधम गुजरात — निजी क्षेत्र रोजगार और भर्ती मेले (डेमो)"
            ),
            organization = LocalizedText(
                gu = "રોજગાર અને તાલીમ નિયામકશ્રી (DET), ગુજરાત સરકાર",
                hi = "रोजगार और प्रशिक्षण निदेशालय (DET), गुजरात सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "અમદાવાદ, સુરત, વડોદરા, રાજકોટ, ભરૂચ અને GIDC ઉદ્યોગોમાં ચકાસાયેલ ખાનગી કંપનીઓની નોકરીઓ અને જિલ્લા રોજગાર ભરતી મેળાની સત્તાવાર માહિતી.",
                hi = "अहमदाबाद, सूरत, वडोदरा, राजकोट, भरूच और GIDC उद्योगों में सत्यापित निजी कंपनियों की नौकरियां और जिला रोजगार मेले की आधिकारिक जानकारी।"
            ),
            eligibility = LocalizedText(
                gu = "ધોરણ 10 / 12 / ITI / ડિપ્લોમા / સ્નાતક / એન્જિનિયર — અનુભવી અને ફ્રેશર ઉમેદવારો માટે અલગ-અલગ પ્રાઇવેટ જોબ રોલ્સ.",
                hi = "10वीं / 12वीं / ITI / डिप्लोमा / स्नातक / इंजीनियर — फ्रेशर और अनुभवी उम्मीदवारों के लिए विभिन्न निजी क्षेत्र के पद।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — જિલ્લાવાર ચાલુ પ્રાઇવેટ નોકરીઓ અને જોબ ફેર તારીખો અનુબંધમ પોર્ટલ પર લાઇવ જુઓ.",
                hi = "डेमो प्रविष्टि — जिलेवार चालू प्राइवेट नौकरियां और जॉब फेयर तिथियां अनुबंधम पोर्टल पर लाइव देखें।"
            ),
            publishedDate = "2026-11-20",
            officialSourceName = "Anubandham Gujarat (anubandham.gujarat.gov.in)",
            officialUrl = "https://anubandham.gujarat.gov.in",
            districtScope = LocalizedText("અમદાવાદ, સુરત, વડોદરા, રાજકોટ અને તમામ જિલ્લા", "अहमदाबाद, सूरत, वडोदरा, राजकोट और सभी जिले"),
            tags = listOf("Private Jobs", "Anubandham", "ITI", "GIDC", "Job Fair"),
            requiredDocuments = listOf(
                LocalizedText("અપડેટેડ બાયોડેટા (Resume / CV)", "अपडेटेड बायोडाटा (Resume / CV)"),
                LocalizedText("આધાર કાર્ડ અને શૈક્ષણિક પ્રમાણપત્રો", "आधार कार्ड और शैक्षणिक प्रमाण-पत्र"),
                LocalizedText("ITI / ડિપ્લોમા / અનુભવ પ્રમાણપત્ર (લાગુ પડતું હોય તો)", "ITI / डिप्लोमा / अनुभव प्रमाण-पत्र (यदि लागू हो)")
            ),
            howToApplySteps = listOf(
                LocalizedText("anubandham.gujarat.gov.in પર 'Jobseeker' તરીકે મફત રજિસ્ટ્રેશન કરો.", "anubandham.gujarat.gov.in पर 'Jobseeker' के रूप में निःशुल्क रजिस्ट्रेशन करें।"),
                LocalizedText("તમારો જિલ્લો અને લાયકાત પસંદ કરી વેરિફાઇડ ખાનગી નોકરીઓ શોધો.", "अपना जिला और योग्यता चुनकर सत्यापित निजी नौकरियां खोजें।"),
                LocalizedText("સીધી ઓનલાઈન અરજી કરો અથવા જિલ્લા ભરતી મેળામાં ભાગ લો.", "सीधे ऑनलाइन आवेदन करें या जिला रोजगार मेले में भाग लें।")
            ),
            isDemo = true
        ),
        PortalUpdateItem(
            id = "job_priv_ncs",
            category = PortalCategory.PRIVATE_JOBS,
            title = LocalizedText(
                gu = "નેશનલ કરિયર સર્વિસ (NCS) — ગુજરાત પ્રાઇવેટ સેક્ટર જોબ્સ (ડેમો)",
                hi = "नेशनल करियर सर्विस (NCS) — गुजरात प्राइवेट सेक्टर जॉब्स (डेमो)"
            ),
            organization = LocalizedText(
                gu = "શ્રમ અને રોજગાર મંત્રાલય, ભારત સરકાર",
                hi = "श्रम और रोजगार मंत्रालय, भारत सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "ગુજરાતના IT, ફાર્મા, ટેક્સટાઇલ, ઓટોમોબાઇલ, બેન્કિંગ અને મેન્યુફેક્ચરિંગ ક્ષેત્રની ચકાસાયેલ ખાનગી કંપનીઓની નોકરીઓ મફતમાં શોધવા માટેનું કેન્દ્ર સરકારનું પોર્ટલ.",
                hi = "गुजरात के IT, फार्मा, टेक्सटाइल, ऑटोमोबाइल, बैंकिंग और मैन्युफैक्चरिंग क्षेत्र की सत्यापित निजी कंपनियों की नौकरियां निःशुल्क खोजने का केंद्र सरकार पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "ધોરણ 10 થી પોસ્ટ ગ્રેજ્યુએટ, ટેકનિકલ અને નોન-ટેકનિકલ ઉમેદવારો માટે. કોઈ રજિસ્ટ્રેશન ફી નથી.",
                hi = "10वीं से पोस्ट ग्रेजुएट, तकनीकी और गैर-तकनीकी उम्मीदवारों के लिए। कोई पंजीकरण शुल्क नहीं।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — ગુજરાત રાજ્ય ફિલ્ટર લગાવીને લાઇવ ખાનગી વેકેન્સી ncs.gov.in પર ચકાસો.",
                hi = "डेमो प्रविष्टि — गुजरात राज्य फ़िल्टर लगाकर लाइव प्राइवेट वैकेंसी ncs.gov.in पर जांचें।"
            ),
            publishedDate = "2026-11-19",
            officialSourceName = "National Career Service (ncs.gov.in)",
            officialUrl = "https://www.ncs.gov.in",
            districtScope = LocalizedText("અમદાવાદ, સુરત, વડોદરા, રાજકોટ, જામનગર, કચ્છ", "अहमदाबाद, सूरत, वडोदरा, राजकोट, जामनगर, कच्छ"),
            tags = listOf("Private Jobs", "NCS", "IT", "Pharma", "Textile", "Free"),
            requiredDocuments = listOf(
                LocalizedText("આધાર નંબર / UAN / PAN (પ્રોફાઇલ વેરિફિકેશન માટે)", "आधार नंबर / UAN / PAN (प्रोफ़ाइल वेरिफिकेशन के लिए)"),
                LocalizedText("શૈક્ષણિક લાયકાત વિગતો અને Resume PDF", "शैक्षणिक योग्यता विवरण और Resume PDF")
            ),
            howToApplySteps = listOf(
                LocalizedText("ncs.gov.in પર જઈ Jobseeker એકાઉન્ટ બનાવો.", "ncs.gov.in पर जाकर Jobseeker अकाउंट बनाएं।"),
                LocalizedText("Location માં 'Gujarat' અને તમારું શહેર પસંદ કરો.", "Location में 'Gujarat' और अपना शहर चुनें।"),
                LocalizedText("અધિકૃત કંપની પ્રોફાઇલ ચકાસી મફતમાં અરજી કરો (કોઈને પૈસા ચૂકવશો નહીં).", "आधिकारिक कंपनी प्रोफ़ाइल जांचकर निःशुल्क आवेदन करें (किसी को पैसे न दें)।")
            ),
            isDemo = true
        ),

        // ==========================================
        // 3. SCHOLARSHIPS (ST, SC, OBC, General)
        // ==========================================
        PortalUpdateItem(
            id = "sch_digital_gujarat_st_sc_obc",
            category = PortalCategory.SCHOLARSHIPS,
            title = LocalizedText(
                gu = "ડિજિટલ ગુજરાત પોસ્ટ-મેટ્રિક સ્કોલરશિપ (ST / SC / OBC-SEBC) — ડેમો",
                hi = "डिजिटल गुजरात पोस्ट-मैट्रिक स्कॉलरशिप (ST / SC / OBC-SEBC) — डेमो"
            ),
            organization = LocalizedText(
                gu = "સામાજિક ન્યાય અને અધિકારીતા / આદિજાતિ વિકાસ વિભાગ, ગુજરાત",
                hi = "सामाजिक न्याय एवं अधिकारिता / जनजातीय विकास विभाग, गुजरात"
            ),
            shortSummary = LocalizedText(
                gu = "અનુસૂચિત જનજાતિ (ST), અનુસૂચિત જાતિ (SC) અને સામાજિક-શૈક્ષણિક પછાત વર્ગ (OBC/SEBC) ના ધોરણ 11 થી કોલેજ/ITI/ડિપ્લોમા વિદ્યાર્થીઓ માટે શિષ્યવૃત્તિ પોર્ટલ.",
                hi = "अनुसूचित जनजाति (ST), अनुसूचित जाति (SC) और अन्य पिछड़ा वर्ग (OBC/SEBC) के कक्षा 11 से कॉलेज/ITI/डिप्लोमा छात्रों के लिए छात्रवृत्ति पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "ગુજરાતના મૂળ નિવાસી ST / SC / OBC વિદ્યાર્થીઓ; આવક મર્યાદા દરેક યોજનાના તાજા પરિપત્ર મુજબ (Digital Gujarat પોર્ટલ પર ચકાસો).",
                hi = "गुजरात के मूल निवासी ST / SC / OBC छात्र; आय सीमा प्रत्येक योजना के नवीनतम परिपत्र के अनुसार (Digital Gujarat पोर्टल पर जांचें)।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — શૈક્ષણિક વર્ષની અરજી ખૂલવાની અને છેલ્લી તારીખ digitalgujarat.gov.in પર ચકાસો.",
                hi = "डेमो प्रविष्टि — शैक्षणिक वर्ष के आवेदन खुलने और अंतिम तिथि digitalgujarat.gov.in पर जांचें।"
            ),
            publishedDate = "2026-11-20",
            officialSourceName = "Digital Gujarat (digitalgujarat.gov.in)",
            officialUrl = "https://www.digitalgujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("ST", "SC", "OBC", "SEBC", "Digital Gujarat", "Scholarship"),
            requiredDocuments = listOf(
                LocalizedText("જાતિનું પ્રમાણપત્ર અને મામલતદાર/TDO નો આવકનો દાખલો", "जाति प्रमाण-पत्र और तहसीलदार/मामलतदार का आय प्रमाण-पत्र"),
                LocalizedText("આધાર કાર્ડ (બેંક ખાતા સાથે DBT સીડેડ)", "आधार कार्ड (बैंक खाते से DBT लिंक)"),
                LocalizedText("છેલ્લા વર્ષની માર્કશીટ, ફી રસીદ અને બોનાફાઇડ સર્ટિફિકેટ", "पिछले वर्ष की मार्कशीट, फीस रसीद और बोनाफाइड सर्टिफिकेट"),
                LocalizedText("બેંક પાસબુકની પ્રથમ પાનાની નકલ", "बैंक पासबुक के पहले पृष्ठ की प्रति")
            ),
            howToApplySteps = listOf(
                LocalizedText("digitalgujarat.gov.in પર Student Registration / Login કરો.", "digitalgujarat.gov.in पर Student Registration / Login करें।"),
                LocalizedText("Scholarship Services માં જઈ તમારી કેટેગરી (ST/SC/SEBC) ની યોજના પસંદ કરો.", "Scholarship Services में जाकर अपनी श्रेणी (ST/SC/SEBC) की योजना चुनें।"),
                LocalizedText("દસ્તાવેજો અપલોડ કરી ફોર્મ સબમિટ કરો અને પ્રિન્ટ કોલેજ/શાળામાં જમા કરો.", "दस्तावेज़ अपलोड कर फॉर्म सबमिट करें और प्रिंट कॉलेज/स्कूल में जमा करें।")
            ),
            scholarshipCategories = listOf(ScholarshipCategory.ST, ScholarshipCategory.SC, ScholarshipCategory.OBC),
            isDemo = true
        ),
        PortalUpdateItem(
            id = "sch_mysy_general",
            category = PortalCategory.SCHOLARSHIPS,
            title = LocalizedText(
                gu = "MYSY — મુખ્યમંત્રી યુવા સ્વાવલંબન યોજના (General / તમામ વર્ગ) — ડેમો",
                hi = "MYSY — मुख्यमंत्री युवा स्वावलंबन योजना (General / सभी वर्ग) — डेमो"
            ),
            organization = LocalizedText(
                gu = "શિક્ષણ વિભાગ, ગુજરાત સરકાર",
                hi = "शिक्षा विभाग, गुजरात सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "ધોરણ 10 કે 12 માં ઊંચી પર્સેન્ટાઇલ મેળવનાર બિન અનામત (General) અને તમામ લાયક વિદ્યાર્થીઓને ટ્યુશન ફી, હોસ્ટેલ અને પુસ્તક સહાય માટેનું પોર્ટલ.",
                hi = "कक्षा 10 या 12 में उच्च पर्सेंटाइल प्राप्त करने वाले सामान्य (General) और सभी पात्र छात्रों को ट्यूशन फीस, हॉस्टल और पुस्तक सहायता का पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "ધોરણ 10/12 માં સત્તાવાર નિયમો મુજબ લઘુત્તમ પર્સેન્ટાઇલ અને કુટુંબની વાર્ષિક આવક મર્યાદા (તાજા નિયમો mysy.guj.nic.in પર જુઓ).",
                hi = "कक्षा 10/12 में आधिकारिक नियमों के अनुसार न्यूनतम पर्सेंटाइल और पारिवारिक वार्षिक आय सीमा (नवीनतम नियम mysy.guj.nic.in पर देखें)।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — Fresh અને Renewal અરજીની તારીખો mysy.guj.nic.in પર તપાસો.",
                hi = "डेमो प्रविष्टि — Fresh और Renewal आवेदन तिथियां mysy.guj.nic.in पर जांचें।"
            ),
            publishedDate = "2026-11-19",
            officialSourceName = "MYSY Gujarat (mysy.guj.nic.in)",
            officialUrl = "https://mysy.guj.nic.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("General", "MYSY", "Open Category", "Higher Education", "Scholarship"),
            requiredDocuments = listOf(
                LocalizedText("ધોરણ 10 / 12 ની બોર્ડ માર્કશીટ અને એડમિશન લેટર", "10वीं / 12वीं की बोर्ड मार्कशीट और एडमिशन लेटर"),
                LocalizedText("આવકનું પ્રમાણપત્ર અને આધાર કાર્ડ", "आय प्रमाण-पत्र और आधार कार्ड"),
                LocalizedText("ફી ભર્યાની રસીદ, સંસ્થા પ્રમાણપત્ર અને બેંક વિગતો", "फीस रसीद, संस्था प्रमाण-पत्र और बैंक विवरण")
            ),
            howToApplySteps = listOf(
                LocalizedText("mysy.guj.nic.in પર Registration કરો અને વિગતો ચકાસો.", "mysy.guj.nic.in पर Registration करें और विवरण जांचें।"),
                LocalizedText("જરૂરી દસ્તાવેજો અપલોડ કરી ઓનલાઈન અરજી લોક કરો.", "आवश्यक दस्तावेज़ अपलोड कर ऑनलाइन आवेदन लॉक करें।"),
                LocalizedText("નજીકના MYSY હેલ્પ સેન્ટર પર દસ્તાવેજ વેરિફિકેશન કરાવો.", "नजदीकी MYSY हेल्प सेंटर पर दस्तावेज़ सत्यापन करवाएं।")
            ),
            scholarshipCategories = listOf(ScholarshipCategory.GENERAL, ScholarshipCategory.OBC, ScholarshipCategory.SC, ScholarshipCategory.ST),
            isDemo = true
        ),
        PortalUpdateItem(
            id = "sch_nsp_central",
            category = PortalCategory.SCHOLARSHIPS,
            title = LocalizedText(
                gu = "નેશનલ સ્કોલરશિપ પોર્ટલ (NSP) — કેન્દ્ર સરકાર શિષ્યવૃત્તિ (ડેમો)",
                hi = "नेशनल स्कॉलरशिप पोर्टल (NSP) — केंद्र सरकार छात्रवृत्ति (डेमो)"
            ),
            organization = LocalizedText(
                gu = "ઇલેક્ટ્રોનિક્સ અને IT મંત્રાલય / શિક્ષણ મંત્રાલય, ભારત સરકાર",
                hi = "इलेक्ट्रॉनिक्स और IT मंत्रालय / शिक्षा मंत्रालय, भारत सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "સેન્ટ્રલ સેક્ટર મેરિટ સ્કોલરશિપ, દિવ્યાંગ, લઘુમતી, SC, ST અને AICTE (પ્રગતિ/સક્ષમ) જેવી કેન્દ્રીય શિષ્યવૃત્તિઓ માટે One-Time Registration (OTR) પોર્ટલ.",
                hi = "सेंट्रल सेक्टर मेरिट स्कॉलरशिप, दिव्यांग, अल्पसंख्यक, SC, ST और AICTE (प्रगति/सक्षम) जैसी केंद्रीय छात्रवृत्तियों के लिए OTR पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "યોજના મુજબ ST / SC / OBC / General મેરિટ અને આવક ધોરણો લાગુ પડે છે. સંપૂર્ણ પાત્રતા NSP પોર્ટલ પર ઉપલબ્ધ છે.",
                hi = "योजना के अनुसार ST / SC / OBC / General मेरिट और आय मानदंड लागू हैं। पूर्ण पात्रता NSP पोर्टल पर उपलब्ध है।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — દરેક સ્કીમની ઓપનિંગ અને ક્લોઝિંગ તારીખ scholarships.gov.in પર જુઓ.",
                hi = "डेमो प्रविष्टि — प्रत्येक स्कीम की ओपनिंग और क्लोजिंग तिथि scholarships.gov.in पर देखें।"
            ),
            publishedDate = "2026-11-18",
            officialSourceName = "National Scholarship Portal (scholarships.gov.in)",
            officialUrl = "https://scholarships.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત અને ભારત", "संपूर्ण गुजरात और भारत"),
            tags = listOf("NSP", "ST", "SC", "OBC", "General", "Central Scholarship"),
            requiredDocuments = listOf(
                LocalizedText("આધાર કાર્ડ અને NSP OTR નંબર", "आधार कार्ड और NSP OTR नंबर"),
                LocalizedText("આવક અને જાતિ/કેટેગરી પ્રમાણપત્ર", "आय और जाति/श्रेणी प्रमाण-पत्र"),
                LocalizedText("છેલ્લી પરીક્ષાની માર્કશીટ અને બેંક ખાતું", "पिछली परीक्षा की मार्कशीट और बैंक खाता")
            ),
            howToApplySteps = listOf(
                LocalizedText("scholarships.gov.in પર આધાર ફેસ ઓથેન્ટિકેશનથી OTR જનરેટ કરો.", "scholarships.gov.in पर आधार फेस ऑथेंटिकेशन से OTR जनरेट करें।"),
                LocalizedText("લાયક સ્કીમ પસંદ કરી અરજી ફોર્મ સબમિટ કરો.", "पात्र स्कीम चुनकर आवेदन फॉर्म सबमिट करें।")
            ),
            scholarshipCategories = listOf(ScholarshipCategory.ST, ScholarshipCategory.SC, ScholarshipCategory.OBC, ScholarshipCategory.GENERAL),
            isDemo = true
        ),

        // ==========================================
        // 4. COLLEGE ADMISSIONS (BA, BCom, BSc, ITI, PTC, Diploma)
        // ==========================================
        PortalUpdateItem(
            id = "adm_gcas_ug",
            category = PortalCategory.COLLEGE_ADMISSIONS,
            title = LocalizedText(
                gu = "GCAS ગુજરાત — BA, BCom, BSc, BBA, BCA કોલેજ એડમિશન પોર્ટલ (ડેમો)",
                hi = "GCAS गुजरात — BA, BCom, BSc, BBA, BCA कॉलेज एडमिशन पोर्टल (डेमो)"
            ),
            organization = LocalizedText(
                gu = "ગુજરાત કોમન એડમિશન સર્વિસીસ (GCAS), ઉચ્ચ શિક્ષણ વિભાગ",
                hi = "गुजरात कॉमन एडमिशन सर्विसेज (GCAS), उच्च शिक्षा विभाग"
            ),
            shortSummary = LocalizedText(
                gu = "ગુજરાતની તમામ સરકારી યુનિવર્સિટીઓ અને સંલગ્ન કોલેજોમાં BA, BCom, BSc, BBA, BCA અને PG પ્રવેશ માટેનું એકીકૃત ઓનલાઈન પ્રવેશ પોર્ટલ.",
                hi = "गुजरात के सभी सरकारी विश्वविद्यालयों और संबद्ध कॉलेजों में BA, BCom, BSc, BBA, BCA और PG प्रवेश के लिए एकीकृत ऑनलाइन पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "UG (BA / BCom / BSc) માટે ધોરણ 12 પાસ (સંબંધિત પ્રવાહ મુજબ) અને PG માટે માન્ય સ્નાતક ડિગ્રી.",
                hi = "UG (BA / BCom / BSc) के लिए 12वीं पास (संबंधित स्ट्रीम अनुसार) और PG के लिए मान्यता प्राप्त स्नातक डिग्री।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — પ્રવેશ રાઉન્ડ, ચોઇસ ફિલિંગ અને તારીખો gcas.gujgov.edu.in પર તપાસો.",
                hi = "डेमो प्रविष्टि — प्रवेश राउंड, चॉइस फिलिंग और तिथियां gcas.gujgov.edu.in पर जांचें।"
            ),
            publishedDate = "2026-11-20",
            officialSourceName = "GCAS Gujarat (gcas.gujgov.edu.in)",
            officialUrl = "https://gcas.gujgov.edu.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત યુનિવર્સિટીઓ", "संपूर्ण गुजरात विश्वविद्यालय"),
            tags = listOf("GCAS", "BA", "BCom", "BSc", "College Admission", "UG"),
            requiredDocuments = listOf(
                LocalizedText("ધોરણ 10 અને ધોરણ 12 ની માર્કશીટ અને સ્કૂલ લિવિંગ સર્ટિફિકેટ (LC)", "10वीं और 12वीं की मार्कशीट और स्कूल लीविंग सर्टिफिकेट (LC)"),
                LocalizedText("જાતિ / EWS / નોન-ક્રિમીલેયર પ્રમાણપત્ર (લાગુ પડતું હોય તો)", "जाति / EWS / नॉन-क्रीमीलेयर प्रमाण-पत्र (यदि लागू हो)"),
                LocalizedText("પાસપોર્ટ ફોટો, સહી અને આધાર કાર્ડ", "पासपोर्ट फोटो, हस्ताक्षर और आधार कार्ड")
            ),
            howToApplySteps = listOf(
                LocalizedText("gcas.gujgov.edu.in પર 'Apply Now' દ્વારા રજિસ્ટ્રેશન કરો.", "gcas.gujgov.edu.in पर 'Apply Now' से रजिस्ट्रेशन करें।"),
                LocalizedText("યુનિવર્સિટી, કોલેજ અને BA/BCom/BSc કોર્સની પસંદગી (Choice) ભરો.", "यूनिवर्सिटी, कॉलेज और BA/BCom/BSc कोर्स की प्राथमिकता (Choice) भरें।"),
                LocalizedText("નજીકના વેરિફિકેશન સેન્ટર પર દસ્તાવેજ ચકાસણી કરાવો.", "नजदीकी वेरिफिकेशन सेंटर पर दस्तावेज़ सत्यापन करवाएं।")
            ),
            admissionStream = AdmissionStream.DEGREE_BA_BCOM_BSC,
            isDemo = true
        ),
        PortalUpdateItem(
            id = "adm_iti_gujarat",
            category = PortalCategory.COLLEGE_ADMISSIONS,
            title = LocalizedText(
                gu = "ગુજરાત ITI એડમિશન પોર્ટલ — સરકારી અને ગ્રાન્ટ-ઇન-એઇડ ITI (ડેમો)",
                hi = "गुजरात ITI एडमिशन पोर्टल — सरकारी और ग्रांट-इन-एड ITI (डेमो)"
            ),
            organization = LocalizedText(
                gu = "રોજગાર અને તાલીમ નિયામકશ્રી (DET), ગુજરાત સરકાર",
                hi = "रोजगार और प्रशिक्षण निदेशालय (DET), गुजरात सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "ઇલેક્ટ્રિશિયન, ફિટર, વાયરમેન, COPA, મિકેનિક અને અન્ય કૌશલ્ય ટ્રેડ્સમાં ઓનલાઈન પ્રવેશ માટેનું સત્તાવાર ગુજરાત ITI પોર્ટલ.",
                hi = "इलेक्ट्रीशियन, फिटर, वायरमैन, COPA, मैकेनिक और अन्य कौशल ट्रेड में ऑनलाइन प्रवेश के लिए आधिकारिक गुजरात ITI पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "ટ્રેડ મુજબ ધોરણ 8 પાસ અથવા ધોરણ 10 પાસ. ન્યૂનતમ વય 14 વર્ષ (સત્તાવાર ITI પ્રવેશ બ્રોશર પોર્ટલ પર વાંચો).",
                hi = "ट्रेड के अनुसार 8वीं पास या 10वीं पास। न्यूनतम आयु 14 वर्ष (आधिकारिक ITI प्रवेश विवरणिका पोर्टल पर पढ़ें)।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — પ્રવેશ ફોર્મ અને રાઉન્ડની તારીખો itiadmission.gujarat.gov.in પર ઉપલબ્ધ છે.",
                hi = "डेमो प्रविष्टि — प्रवेश फॉर्म और राउंड की तिथियां itiadmission.gujarat.gov.in पर उपलब्ध हैं।"
            ),
            publishedDate = "2026-11-19",
            officialSourceName = "Gujarat ITI Admission (itiadmission.gujarat.gov.in)",
            officialUrl = "https://itiadmission.gujarat.gov.in",
            districtScope = LocalizedText("તમામ તાલુકા અને જિલ્લા ITI", "सभी तालुका और जिला ITI"),
            tags = listOf("ITI", "Electrician", "Fitter", "COPA", "10th Pass"),
            requiredDocuments = listOf(
                LocalizedText("ધોરણ 8 / 10 ની માર્કશીટ અને LC", "8वीं / 10वीं की मार्कशीट और LC"),
                LocalizedText("જાતિનું પ્રમાણપત્ર અને આધાર કાર્ડ", "जाति प्रमाण-पत्र और आधार कार्ड"),
                LocalizedText("પાસપોર્ટ સાઇઝ ફોટો અને સક્રિય મોબાઇલ નંબર", "पासपोर्ट साइज़ फोटो और सक्रिय मोबाइल नंबर")
            ),
            howToApplySteps = listOf(
                LocalizedText("itiadmission.gujarat.gov.in પર નવું ઉમેદવાર રજિસ્ટ્રેશન કરો.", "itiadmission.gujarat.gov.in पर नया उम्मीदवार पंजीकरण करें।"),
                LocalizedText("નજીકની સરકારી ITI પર અસલ દસ્તાવેજો સાથે ફોર્મ કન્ફર્મ કરાવો.", "नजदीकी सरकारी ITI में मूल दस्तावेज़ों के साथ फॉर्म कन्फर्म करवाएं।"),
                LocalizedText("ઓનલાઈન ટ્રેડ અને ITI પસંદગી (Choice Filling) લોક કરો.", "ऑनलाइन ट्रेड और ITI चॉइस फिलिंग लॉक करें।")
            ),
            admissionStream = AdmissionStream.ITI,
            isDemo = true
        ),
        PortalUpdateItem(
            id = "adm_ptc_deled",
            category = PortalCategory.COLLEGE_ADMISSIONS,
            title = LocalizedText(
                gu = "PTC (D.El.Ed.) પ્રાથમિક શિક્ષક તાલીમ પ્રવેશ માર્ગદર્શિકા — ડેમો",
                hi = "PTC (D.El.Ed.) प्राथमिक शिक्षक प्रशिक्षण प्रवेश गाइड — डेमो"
            ),
            organization = LocalizedText(
                gu = "ગુજરાત રાજ્ય શિક્ષણ વિભાગ / રાજ્ય પરીક્ષા બોર્ડ (SEB)",
                hi = "गुजरात राज्य शिक्षा विभाग / राज्य परीक्षा बोर्ड (SEB)"
            ),
            shortSummary = LocalizedText(
                gu = "પ્રાથમિક શિક્ષક બનવા માટે 2 વર્ષીય PTC (D.El.Ed.) કોર્સની કેન્દ્રીયકૃત પ્રવેશ પ્રક્રિયા, DIET કોલેજો અને સત્તાવાર પ્રવેશ સૂચનાઓ.",
                hi = "प्राथमिक शिक्षक बनने हेतु 2 वर्षीय PTC (D.El.Ed.) कोर्स की केंद्रीकृत प्रवेश प्रक्रिया, DIET कॉलेजों और आधिकारिक प्रवेश सूचनाएं।"
            ),
            eligibility = LocalizedText(
                gu = "ધોરણ 12 પાસ (સામાન્ય / વિજ્ઞાન / કોમર્સ પ્રવાહ) — NCTE અને ગુજરાત પ્રવેશ સમિતિના લઘુત્તમ ટકાવારી ધોરણો મુજબ.",
                hi = "12वीं पास (कला / विज्ञान / वाणिज्य संकाय) — NCTE और गुजरात प्रवेश समिति के न्यूनतम प्रतिशत मानदंडों के अनुसार।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — PTC પ્રવેશની ચાલુ નોટિફિકેશન અને તારીખો અધિકૃત શિક્ષણ પોર્ટલ પર ચકાસો.",
                hi = "डेमो प्रविष्टि — PTC प्रवेश की नवीनतम अधिसूचना और तिथियां आधिकारिक शिक्षा पोर्टल पर जांचें।"
            ),
            publishedDate = "2026-11-18",
            officialSourceName = "SEB / Gujarat Education (sebexam.org)",
            officialUrl = "https://www.sebexam.org",
            districtScope = LocalizedText("તમામ જિલ્લા DIET અને PTC કોલેજો", "सभी जिला DIET और PTC कॉलेज"),
            tags = listOf("PTC", "D.El.Ed", "DIET", "Teacher Training", "12th Pass"),
            requiredDocuments = listOf(
                LocalizedText("ધોરણ 10 અને 12 ની માર્કશીટ, LC અને પ્રયત્ન પ્રમાણપત્ર", "10वीं और 12वीं की मार्कशीट, LC और प्रयास प्रमाण-पत्र"),
                LocalizedText("અનામત કેટેગરી પ્રમાણપત્ર (લાગુ પડતું હોય તો)", "आरक्षण श्रेणी प्रमाण-पत्र (यदि लागू हो)")
            ),
            howToApplySteps = listOf(
                LocalizedText("સત્તાવાર પ્રવેશ જાહેરાત અને કોલેજ યાદી વેબસાઇટ પર વાંચો.", "आधिकारिक प्रवेश अधिसूचना और कॉलेज सूची वेबसाइट पर पढ़ें।"),
                LocalizedText("ઓનલાઈન ફોર્મ ભરી હેલ્પ સેન્ટર પર ચકાસણી અને ચોઇસ ફિલિંગ કરો.", "ऑनलाइन फॉर्म भरकर हेल्प सेंटर पर सत्यापन और चॉइस फिलिंग करें।")
            ),
            admissionStream = AdmissionStream.PTC,
            isDemo = true
        ),
        PortalUpdateItem(
            id = "adm_acpc_diploma_engg",
            category = PortalCategory.COLLEGE_ADMISSIONS,
            title = LocalizedText(
                gu = "ACPC / ACPDC ગુજરાત — ડિપ્લોમા, એન્જિનિયરિંગ અને ફાર્મસી પ્રવેશ (ડેમો)",
                hi = "ACPC / ACPDC गुजरात — डिप्लोमा, इंजीनियरिंग और फार्मेसी प्रवेश (डेमो)"
            ),
            organization = LocalizedText(
                gu = "એડમિશન કમિટી ફોર પ્રોફેશનલ કોર્સીસ (ACPC), ગુજરાત",
                hi = "एडमिशन कमिटी फॉर प्रोफेशनल कोर्सेज (ACPC), गुजरात"
            ),
            shortSummary = LocalizedText(
                gu = "ધોરણ 10 પછી ડિપ્લોમા એન્જિનિયરિંગ (ACPDC) તથા ધોરણ 12 પછી B.E./B.Tech અને ફાર્મસી (ACPC) ની કેન્દ્રીયકૃત પ્રવેશ પ્રક્રિયા.",
                hi = "10वीं के बाद डिप्लोमा इंजीनियरिंग (ACPDC) तथा 12वीं के बाद B.E./B.Tech और फार्मेसी (ACPC) की केंद्रीकृत प्रवेश प्रक्रिया।"
            ),
            eligibility = LocalizedText(
                gu = "ડિપ્લોમા માટે ધોરણ 10 પાસ; ડિગ્રી એન્જિનિયરિંગ / ફાર્મસી માટે ધોરણ 12 વિજ્ઞાન + GUJCET / JEE (સત્તાવાર નિયમો મુજબ).",
                hi = "डिप्लोमा के लिए 10वीं पास; डिग्री इंजीनियरिंग / फार्मेसी के लिए 12वीं विज्ञान + GUJCET / JEE (आधिकारिक नियमों अनुसार)।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — પ્રવેશ સમયપત્રક અને કટ-ઓફ acpc.gujarat.gov.in પર જુઓ.",
                hi = "डेमो प्रविष्टि — प्रवेश समय-सारणी और कट-ऑफ acpc.gujarat.gov.in पर देखें।"
            ),
            publishedDate = "2026-11-17",
            officialSourceName = "ACPC Gujarat (acpc.gujarat.gov.in)",
            officialUrl = "https://acpc.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("ACPC", "ACPDC", "Diploma", "Engineering", "Pharmacy"),
            requiredDocuments = listOf(
                LocalizedText("ધોરણ 10 / 12 માર્કશીટ અને GUJCET સ્કોરકાર્ડ", "10वीं / 12वीं मार्कशीट और GUJCET स्कोरकार्ड"),
                LocalizedText("LC, જાતિ / EWS / TFWS આવક પ્રમાણપત્ર", "LC, जाति / EWS / TFWS आय प्रमाण-पत्र")
            ),
            howToApplySteps = listOf(
                LocalizedText("acpc.gujarat.gov.in પરથી ઓનલાઈન રજિસ્ટ્રેશન અને ફી ભરો.", "acpc.gujarat.gov.in से ऑनलाइन पंजीकरण और शुल्क जमा करें।"),
                LocalizedText("મોક રાઉન્ડ અને મુખ્ય રાઉન્ડમાં કોલેજ પસંદગી ગોઠવો.", "मॉक राउंड और मुख्य राउंड में कॉलेज प्राथमिकता तय करें।")
            ),
            admissionStream = AdmissionStream.DIPLOMA_ENGG,
            isDemo = true
        ),

        // ==========================================
        // 5. MERIT LISTS
        // ==========================================
        PortalUpdateItem(
            id = "merit_gcas_rounds",
            category = PortalCategory.MERIT_LISTS,
            title = LocalizedText(
                gu = "GCAS કોલેજ એડમિશન મેરિટ લિસ્ટ (BA / BCom / BSc) — સત્તાવાર લિંક (ડેમો)",
                hi = "GCAS कॉलेज एडमिशन मेरिट लिस्ट (BA / BCom / BSc) — आधिकारिक लिंक (डेमो)"
            ),
            organization = LocalizedText(
                gu = "ગુજરાત કોમન એડમિશન સર્વિસીસ (GCAS)",
                hi = "गुजरात कॉमन एडमिशन सर्विसेज (GCAS)"
            ),
            shortSummary = LocalizedText(
                gu = "ગુજરાતની સરકારી યુનિવર્સિટીઓના રાઉન્ડ-1, રાઉન્ડ-2 અને મોપ-અપ રાઉન્ડના મેરિટ લિસ્ટ તથા સીટ એલોટમેન્ટ સ્ટેટસ ચકાસવા માટેની સત્તાવાર લિંક.",
                hi = "गुजरात के सरकारी विश्वविद्यालयों के राउंड-1, राउंड-2 और मॉप-अप राउंड की मेरिट लिस्ट और सीट अलॉटमेंट स्टेटस जांचने का आधिकारिक लिंक।"
            ),
            eligibility = LocalizedText(
                gu = "GCAS પર સફળતાપૂર્વક અરજી અને દસ્તાવેજ ચકાસણી પૂર્ણ કરેલ તમામ વિદ્યાર્થીઓ.",
                hi = "GCAS पर सफलतापूर्वक आवेदन और दस्तावेज़ सत्यापन पूरा कर चुके सभी छात्र।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — દરેક રાઉન્ડના મેરિટ લિસ્ટની પ્રસિદ્ધિ તારીખ GCAS ડેશબોર્ડ પર જાહેર થાય છે.",
                hi = "डेमो प्रविष्टि — प्रत्येक राउंड की मेरिट लिस्ट तिथि GCAS डैशबोर्ड पर घोषित होती है।"
            ),
            publishedDate = "2026-11-20",
            officialSourceName = "GCAS Portal (gcas.gujgov.edu.in)",
            officialUrl = "https://gcas.gujgov.edu.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("Merit List", "GCAS", "BA", "BCom", "BSc", "Seat Allotment"),
            requiredDocuments = listOf(
                LocalizedText("GCAS એપ્લિકેશન નંબર અને પાસવર્ડ", "GCAS एप्लीकेशन नंबर और पासवर्ड"),
                LocalizedText("પ્રવેશ કન્ફર્મેશન માટે અસલ માર્કશીટ અને ફી રસીદ", "प्रवेश कन्फर्मेशन के लिए मूल मार्कशीट और फीस रसीद")
            ),
            howToApplySteps = listOf(
                LocalizedText("gcas.gujgov.edu.in પર લોગિન કરો અને 'Merit / Offer Letter' ટેબ ખોલો.", "gcas.gujgov.edu.in पर लॉगिन करें और 'Merit / Offer Letter' टैब खोलें।"),
                LocalizedText("ફાળવેલ કોલેજ સ્વીકારી સમયમર્યાદામાં પ્રવેશ કન્ફર્મ કરો.", "आवंटित कॉलेज स्वीकार कर समयसीमा में प्रवेश कन्फर्म करें।")
            ),
            isDemo = true
        ),
        PortalUpdateItem(
            id = "merit_iti_allotment",
            category = PortalCategory.MERIT_LISTS,
            title = LocalizedText(
                gu = "ગુજરાત ITI મેરિટ લિસ્ટ અને સીટ એલોટમેન્ટ — સત્તાવાર લિંક (ડેમો)",
                hi = "गुजरात ITI मेरिट लिस्ट और सीट अलॉटमेंट — आधिकारिक लिंक (डेमो)"
            ),
            organization = LocalizedText(
                gu = "રોજગાર અને તાલીમ નિયામકશ્રી (DET), ગુજરાત",
                hi = "रोजगार और प्रशिक्षण निदेशालय (DET), गुजरात"
            ),
            shortSummary = LocalizedText(
                gu = "ITI પ્રવેશના મોક રાઉન્ડ, રાઉન્ડ-1, રાઉન્ડ-2 નું મેરિટ નંબર, ફાળવેલ ટ્રેડ અને પ્રવેશ સ્લિપ ડાઉનલોડ કરવાની સત્તાવાર પ્રક્રિયા.",
                hi = "ITI प्रवेश के मॉक राउंड, राउंड-1, राउंड-2 का मेरिट नंबर, आवंटित ट्रेड और एडमिशन स्लिप डाउनलोड करने की आधिकारिक प्रक्रिया।"
            ),
            eligibility = LocalizedText(
                gu = "જે ઉમેદવારોએ નજીકની ITI પર ફોર્મ વેરિફિકેશન અને ચોઇસ ફિલિંગ પૂર્ણ કર્યું છે.",
                hi = "जिन उम्मीदवारों ने नजदीकी ITI में फॉर्म वेरिफिकेशन और चॉइस फिलिंग पूरी कर ली है।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — તાજા મેરિટ રાઉન્ડની તારીખો itiadmission.gujarat.gov.in પર ચકાસો.",
                hi = "डेमो प्रविष्टि — नवीनतम मेरिट राउंड की तिथियां itiadmission.gujarat.gov.in पर जांचें।"
            ),
            publishedDate = "2026-11-19",
            officialSourceName = "Gujarat ITI Portal (itiadmission.gujarat.gov.in)",
            officialUrl = "https://itiadmission.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("Merit List", "ITI", "Allotment", "DET Gujarat"),
            requiredDocuments = listOf(
                LocalizedText("ITI રજિસ્ટ્રેશન નંબર અને જન્મ તારીખ", "ITI रजिस्ट्रेशन नंबर और जन्म तिथि"),
                LocalizedText("પ્રવેશ સ્લિપ (Allotment Letter) અને અસલ દસ્તાવેજો", "प्रवेश स्लिप (Allotment Letter) और मूल दस्तावेज़")
            ),
            howToApplySteps = listOf(
                LocalizedText("itiadmission.gujarat.gov.in પર 'Candidate Login' માં પ્રવેશ કરો.", "itiadmission.gujarat.gov.in पर 'Candidate Login' में प्रवेश करें।"),
                LocalizedText("મેરિટ રેન્ક અને એલોટમેન્ટ લેટર ડાઉનલોડ કરી ફાળવેલ ITI માં રિપોર્ટિંગ કરો.", "मेरिट रैंक और अलॉटमेंट लेटर डाउनलोड कर आवंटित ITI में रिपोर्ट करें।")
            ),
            isDemo = true
        ),
        PortalUpdateItem(
            id = "merit_acpc_provisional",
            category = PortalCategory.MERIT_LISTS,
            title = LocalizedText(
                gu = "ACPC પ્રોવિઝનલ અને ફાઇનલ મેરિટ લિસ્ટ (ડિપ્લોમા / ડિગ્રી) — ડેમો",
                hi = "ACPC प्रोविजनल और फाइनल मेरिट लिस्ट (डिप्लोमा / डिग्री) — डेमो"
            ),
            organization = LocalizedText(
                gu = "ACPC ગુજરાત, અમદાવાદ",
                hi = "ACPC गुजरात, अहमदाबाद"
            ),
            shortSummary = LocalizedText(
                gu = "એન્જિનિયરિંગ, ફાર્મસી અને ડિપ્લોમા પ્રવેશ માટે પ્રોવિઝનલ મેરિટ રેન્ક, ફાઇનલ મેરિટ અને કોલેજ-વાઇઝ કટ-ઓફ રેન્ક જોવા માટેનું પોર્ટલ.",
                hi = "इंजीनियरिंग, फार्मेसी और डिप्लोमा प्रवेश के लिए प्रोविजनल मेरिट रैंक, फाइनल मेरिट और कॉलेज-वार कट-ऑफ रैंक देखने का पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "ACPC / ACPDC પોર્ટલ પર રજિસ્ટર્ડ તમામ ઉમેદવારો.",
                hi = "ACPC / ACPDC पोर्टल पर पंजीकृत सभी उम्मीदवार।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — મેરિટ જાહેરાત અને મોક રાઉન્ડ તારીખ acpc.gujarat.gov.in પર ઉપલબ્ધ છે.",
                hi = "डेमो प्रविष्टि — मेरिट घोषणा और मॉक राउंड तिथि acpc.gujarat.gov.in पर उपलब्ध है।"
            ),
            publishedDate = "2026-11-18",
            officialSourceName = "ACPC Official (acpc.gujarat.gov.in)",
            officialUrl = "https://acpc.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("Merit List", "ACPC", "Cutoff", "Engineering", "Diploma"),
            requiredDocuments = listOf(
                LocalizedText("ACPC યુઝર આઇડી અને પાસવર્ડ", "ACPC यूज़र आईडी और पासवर्ड")
            ),
            howToApplySteps = listOf(
                LocalizedText("acpc.gujarat.gov.in પર લોગિન કરી મેરિટ નંબર અને કટ-ઓફ પીડીએફ જુઓ.", "acpc.gujarat.gov.in पर लॉगिन कर मेरिट नंबर और कट-ऑफ पीडीएफ देखें।")
            ),
            isDemo = true
        ),

        // ==========================================
        // 6. GOVERNMENT SCHEMES (Gujarat & Central)
        // ==========================================
        PortalUpdateItem(
            id = "schm_vahli_dikri",
            category = PortalCategory.GOVT_SCHEMES,
            title = LocalizedText(
                gu = "વ્હાલી દીકરી યોજના — ગુજરાત સરકાર (ડેમો માહિતી)",
                hi = "व्हाली दीकरी योजना — गुजरात सरकार (डेमो जानकारी)"
            ),
            organization = LocalizedText(
                gu = "મહિલા અને બાળ વિકાસ વિભાગ (WCD), ગુજરાત સરકાર",
                hi = "महिला एवं बाल विकास विभाग (WCD), गुजरात सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "દીકરીઓના શિક્ષણ અને સશક્તિકરણને પ્રોત્સાહન આપવા માટે ગુજરાત સરકારની મુખ્ય નાણાકીય સહાય યોજના (ધોરણ 1, ધોરણ 9 અને 18 વર્ષે હપ્તાવાર સહાય).",
                hi = "बेटियों की शिक्षा और सशक्तिकरण को बढ़ावा देने हेतु गुजरात सरकार की प्रमुख वित्तीय सहायता योजना (कक्षा 1, कक्षा 9 और 18 वर्ष पर किश्तों में सहायता)।"
            ),
            eligibility = LocalizedText(
                gu = "ગુજરાત રાજ્યના નિવાસી પરિવારની પ્રથમ 3 સંતાનો પૈકીની દીકરીઓ; નિયત વાર્ષિક આવક મર્યાદા લાગુ પડે છે (સત્તાવાર પરિપત્ર પોર્ટલ પર ચકાસો).",
                hi = "गुजरात राज्य के निवासी परिवार की प्रथम 3 संतानों में से बेटियां; निर्धारित वार्षिक आय सीमा लागू है (आधिकारिक परिपत्र पोर्टल पर जांचें)।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — દીકરીના જન્મ બાદ નિયત સમયમર્યાદામાં ઓનલાઈન / આંગણવાડી મારફતે અરજી.",
                hi = "डेमो प्रविष्टि — बेटी के जन्म के बाद निर्धारित समयसीमा में ऑनलाइन / आंगनवाड़ी के माध्यम से आवेदन।"
            ),
            publishedDate = "2026-11-20",
            officialSourceName = "WCD Gujarat (wcd.gujarat.gov.in)",
            officialUrl = "https://wcd.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("Gujarat Scheme", "Girl Child", "WCD", "Financial Aid"),
            requiredDocuments = listOf(
                LocalizedText("દીકરીનો જન્મ દાખલો અને માતા-પિતાનું આધાર કાર્ડ", "बेटी का जन्म प्रमाण-पत्र और माता-पिता का आधार कार्ड"),
                LocalizedText("કુટુંબનો વાર્ષિક આવકનો દાખલો અને રેશન કાર્ડ", "परिवार का वार्षिक आय प्रमाण-पत्र और राशन कार्ड"),
                LocalizedText("માતા-પિતાનું લગ્ન નોંધણી પ્રમાણપત્ર અને બેંક ખાતા વિગત", "माता-पिता का विवाह पंजीकरण प्रमाण-पत्र और बैंक विवरण")
            ),
            howToApplySteps = listOf(
                LocalizedText("wcd.gujarat.gov.in અથવા digitalgujarat.gov.in પર યોજનાની વિગતો વાંચો.", "wcd.gujarat.gov.in या digitalgujarat.gov.in पर योजना का विवरण पढ़ें।"),
                LocalizedText("ઓનલાઈન અથવા નજીકના આંગણવાડી / જિલ્લા મહિલા અને બાળ અધિકારીની કચેરીમાં અરજી કરો.", "ऑनलाइन या नजदीकी आंगनवाड़ी / जिला महिला एवं बाल अधिकारी कार्यालय में आवेदन करें।")
            ),
            schemeScope = SchemeScope.GUJARAT,
            isDemo = true
        ),
        PortalUpdateItem(
            id = "schm_ikhedut",
            category = PortalCategory.GOVT_SCHEMES,
            title = LocalizedText(
                gu = "આઈ-ખેડૂત (iKhedut) કૃષિ અને પશુપાલન સહાય યોજનાઓ — ડેમો",
                hi = "आई-खेडूत (iKhedut) कृषि एवं पशुपालन सहायता योजनाएं — डेमो"
            ),
            organization = LocalizedText(
                gu = "કૃષિ, ખેડૂત કલ્યાણ અને સહકાર વિભાગ, ગુજરાત સરકાર",
                hi = "कृषि, किसान कल्याण एवं सहकारिता विभाग, गुजरात सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "ટ્રેક્ટર, કૃષિ ઓજારો, તાડપત્રી, પશુપાલન, બાગાયત અને મત્સ્યોદ્યોગ સબસિડી માટે ગુજરાતના ખેડૂતોનું એકીકૃત ઓનલાઈન પોર્ટલ.",
                hi = "ट्रैक्टर, कृषि उपकरण, तिरपाल, पशुपालन, बागवानी और मत्स्य पालन सब्सिडी हेतु गुजरात के किसानों का एकीकृत ऑनलाइन पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "ગુજરાતના ખાતેદાર ખેડૂતો (7/12 અને 8-અ ઉતારા ધરાવતા) તથા પશુપાલકો. ઘટકવાર પાત્રતા iKhedut પોર્ટલ પર ચકાસો.",
                hi = "गुजरात के खातेदार किसान (7/12 और 8-अ धारक) तथा पशुपालक। घटकवार पात्रता iKhedut पोर्टल पर जांचें।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — કઈ યોજનાના ફોર્મ હાલમાં ચાલુ છે તે ikhedut.gujarat.gov.in પર લાઇવ જુઓ.",
                hi = "डेमो प्रविष्टि — किस योजना के फॉर्म वर्तमान में खुले हैं, यह ikhedut.gujarat.gov.in पर लाइव देखें।"
            ),
            publishedDate = "2026-11-19",
            officialSourceName = "iKhedut Portal (ikhedut.gujarat.gov.in)",
            officialUrl = "https://ikhedut.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("Gujarat Scheme", "iKhedut", "Farmers", "Subsidy"),
            requiredDocuments = listOf(
                LocalizedText("7/12 અને 8-અ જમીન ઉતારાની નકલ", "7/12 और 8-अ भूमि अभिलेख की प्रति"),
                LocalizedText("આધાર કાર્ડ અને બેંક પાસબુક / રદ કરેલ ચેક", "आधार कार्ड और बैंक पासबुक / कैंसिल चेक"),
                LocalizedText("રેશન કાર્ડ અને મોબાઇલ નંબર", "राशन कार्ड और मोबाइल नंबर")
            ),
            howToApplySteps = listOf(
                LocalizedText("ikhedut.gujarat.gov.in પર 'યોજનાઓ' વિભાગમાં ચાલુ ઘટક પસંદ કરો.", "ikhedut.gujarat.gov.in पर 'योजनाएं' सेक्शन में चालू घटक चुनें।"),
                LocalizedText("ખેડૂત વિગતો અને જમીન ખાતા નંબર ભરી અરજી કન્ફર્મ કરો અને પ્રિન્ટ સાચવો.", "किसान विवरण और भूमि खाता संख्या भरकर आवेदन कन्फर्म करें और प्रिंट सेव करें।")
            ),
            schemeScope = SchemeScope.GUJARAT,
            isDemo = true
        ),
        PortalUpdateItem(
            id = "schm_manav_garima",
            category = PortalCategory.GOVT_SCHEMES,
            title = LocalizedText(
                gu = "માનવ ગરિમા અને કુંવરબાઈનું મામેરું — ઈ-સમાજ કલ્યાણ (ડેમો)",
                hi = "मानव गरिमा और कुंवरबाई नुं मामेरूं — ई-समाज कल्याण (डेमो)"
            ),
            organization = LocalizedText(
                gu = "સામાજિક ન્યાય અને અધિકારીતા વિભાગ, ગુજરાત સરકાર",
                hi = "सामाजिक न्याय एवं अधिकारिता विभाग, गुजरात सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "સ્વરોજગાર માટે વિનામૂલ્યે સાધન કિટ (માનવ ગરિમા) અને દીકરીના લગ્ન સમયે નાણાકીય સહાય (કુંવરબાઈનું મામેરું) જેવી રાજ્ય કલ્યાણ યોજનાઓ.",
                hi = "स्वरोजगार के लिए निःशुल्क टूलकिट (मानव गरिमा) और बेटी के विवाह पर वित्तीय सहायता (कुंवरबाई नुं मामेरूं) जैसी राज्य कल्याण योजनाएं।"
            ),
            eligibility = LocalizedText(
                gu = "ગુજરાતના પાત્ર નાગરિકો — આવક મર્યાદા અને કેટેગરી મુજબની શરતો esamajkalyan.gujarat.gov.in પર ઉપલબ્ધ છે.",
                hi = "गुजरात के पात्र नागरिक — आय सीमा और श्रेणी अनुसार शर्तें esamajkalyan.gujarat.gov.in पर उपलब्ध हैं।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — અરજી સ્ટેટસ અને ચાલુ તારીખો e-Samaj Kalyan પોર્ટલ પર ચકાસો.",
                hi = "डेमो प्रविष्टि — आवेदन स्थिति और चालू तिथियां e-Samaj Kalyan पोर्टल पर जांचें।"
            ),
            publishedDate = "2026-11-18",
            officialSourceName = "e-Samaj Kalyan (esamajkalyan.gujarat.gov.in)",
            officialUrl = "https://esamajkalyan.gujarat.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
            tags = listOf("Gujarat Scheme", "e-Samaj Kalyan", "Self Employment", "Toolkit"),
            requiredDocuments = listOf(
                LocalizedText("આધાર કાર્ડ, રેશન કાર્ડ અને રહેઠાણનો પુરાવો", "आधार कार्ड, राशन कार्ड और निवास प्रमाण-पत्र"),
                LocalizedText("જાતિ અને વાર્ષિક આવકનું પ્રમાણપત્ર", "जाति और वार्षिक आय प्रमाण-पत्र"),
                LocalizedText("બેંક ખાતાની વિગતો (IFSC સાથે)", "बैंक खाते का विवरण (IFSC सहित)")
            ),
            howToApplySteps = listOf(
                LocalizedText("esamajkalyan.gujarat.gov.in પર Citizen Login બનાવો.", "esamajkalyan.gujarat.gov.in पर Citizen Login बनाएं।"),
                LocalizedText("યોગ્ય યોજના પસંદ કરી દસ્તાવેજો અપલોડ કરી અરજી સબમિટ કરો.", "उपयुक्त योजना चुनकर दस्तावेज़ अपलोड करें और आवेदन सबमिट करें।")
            ),
            schemeScope = SchemeScope.GUJARAT,
            isDemo = true
        ),
        PortalUpdateItem(
            id = "schm_pmjay_ayushman",
            category = PortalCategory.GOVT_SCHEMES,
            title = LocalizedText(
                gu = "આયુષ્માન ભારત (PM-JAY / MA કાર્ડ) આરોગ્ય સુરક્ષા યોજના — ડેમો",
                hi = "आयुष्मान भारत (PM-JAY / MA कार्ड) स्वास्थ्य सुरक्षा योजना — डेमो"
            ),
            organization = LocalizedText(
                gu = "નેશનલ હેલ્થ ઓથોરિટી (ભારત સરકાર) અને ગુજરાત આરોગ્ય વિભાગ",
                hi = "नेशनल हेल्थ अथॉरिटी (भारत सरकार) और गुजरात स्वास्थ्य विभाग"
            ),
            shortSummary = LocalizedText(
                gu = "પાત્ર પરિવારોને માન્ય સરકારી અને ખાનગી હોસ્પિટલોમાં કેશલેસ સારવાર પૂરી પાડતી સંયુક્ત કેન્દ્ર અને રાજ્ય આરોગ્ય યોજના.",
                hi = "पात्र परिवारों को सूचीबद्ध सरकारी और निजी अस्पतालों में कैशलेस उपचार प्रदान करने वाली संयुक्त केंद्र एवं राज्य स्वास्थ्य योजना।"
            ),
            eligibility = LocalizedText(
                gu = "NFSA રેશન કાર્ડ ધારકો, SECC યાદીમાં સામેલ પરિવારો અને 70+ વર્ષના વરિષ્ઠ નાગરિકો (તાજી પાત્રતા pmjay.gov.in પર ચકાસો).",
                hi = "NFSA राशन कार्ड धारक, SECC सूची में शामिल परिवार और 70+ वर्ष के वरिष्ठ नागरिक (नवीनतम पात्रता pmjay.gov.in पर जांचें)।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — આયુષ્માન કાર્ડ e-KYC વર્ષભર ચાલુ રહે છે.",
                hi = "डेमो प्रविष्टि — आयुष्मान कार्ड e-KYC वर्ष भर उपलब्ध रहती है।"
            ),
            publishedDate = "2026-11-20",
            officialSourceName = "PM-JAY Official (pmjay.gov.in)",
            officialUrl = "https://pmjay.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત અને ભારત", "संपूर्ण गुजरात और भारत"),
            tags = listOf("Central Scheme", "PM-JAY", "MA Card", "Health", "Ayushman"),
            requiredDocuments = listOf(
                LocalizedText("આધાર કાર્ડ (OTP માટે મોબાઇલ લિંક)", "आधार कार्ड (OTP के लिए मोबाइल लिंक)"),
                LocalizedText("રેશન કાર્ડ / NFSA કુટુંબ કાર્ડ", "राशन कार्ड / NFSA परिवार कार्ड")
            ),
            howToApplySteps = listOf(
                LocalizedText("pmjay.gov.in અથવા beneficiary.nha.gov.in પર તમારી પાત્રતા ચકાસો.", "pmjay.gov.in या beneficiary.nha.gov.in पर अपनी पात्रता जांचें।"),
                LocalizedText("આધાર e-KYC પૂર્ણ કરી આયુષ્માન કાર્ડ ડાઉનલોડ કરો.", "आधार e-KYC पूरा करके आयुष्मान कार्ड डाउनलोड करें।")
            ),
            schemeScope = SchemeScope.CENTRAL,
            isDemo = true
        ),
        PortalUpdateItem(
            id = "schm_pm_kisan",
            category = PortalCategory.GOVT_SCHEMES,
            title = LocalizedText(
                gu = "PM-KISAN — પ્રધાનમંત્રી કિસાન સન્માન નિધિ યોજના (ડેમો)",
                hi = "PM-KISAN — प्रधानमंत्री किसान सम्मान निधि योजना (डेमो)"
            ),
            organization = LocalizedText(
                gu = "કૃષિ અને ખેડૂત કલ્યાણ મંત્રાલય, ભારત સરકાર",
                hi = "कृषि एवं किसान कल्याण मंत्रालय, भारत सरकार"
            ),
            shortSummary = LocalizedText(
                gu = "પાત્ર ખેડૂત પરિવારોને સીધા બેંક ખાતામાં (DBT) વાર્ષિક નાણાકીય સહાય, e-KYC અને લાભાર્થી સ્ટેટસ ચકાસણી માટેનું કેન્દ્રીય પોર્ટલ.",
                hi = "पात्र किसान परिवारों को सीधे बैंक खाते में (DBT) वार्षिक वित्तीय सहायता, e-KYC और लाभार्थी स्थिति जांच का केंद्रीय पोर्टल।"
            ),
            eligibility = LocalizedText(
                gu = "ખેતીલાયક જમીન ધરાવતા પાત્ર ખેડૂત પરિવારો (e-KYC અને Land Seeding ફરજિયાત).",
                hi = "कृषि योग्य भूमि रखने वाले पात्र किसान परिवार (e-KYC और Land Seeding अनिवार्य)।"
            ),
            importantDateNote = LocalizedText(
                gu = "ડેમો એન્ટ્રી — હપ્તાનું સ્ટેટસ અને e-KYC pmkisan.gov.in પર તપાસો.",
                hi = "डेमो प्रविष्टि — किश्त की स्थिति और e-KYC pmkisan.gov.in पर जांचें।"
            ),
            publishedDate = "2026-11-19",
            officialSourceName = "PM-KISAN Official (pmkisan.gov.in)",
            officialUrl = "https://pmkisan.gov.in",
            districtScope = LocalizedText("સમગ્ર ગુજરાત અને ભારત", "संपूर्ण गुजरात और भारत"),
            tags = listOf("Central Scheme", "PM-KISAN", "Farmers", "DBT"),
            requiredDocuments = listOf(
                LocalizedText("આધાર કાર્ડ અને DBT સક્રિય બેંક ખાતું", "आधार कार्ड और DBT सक्रिय बैंक खाता"),
                LocalizedText("જમીનની વિગત (7/12 ઉતારો)", "भूमि विवरण (7/12 रिकॉर्ड)")
            ),
            howToApplySteps = listOf(
                LocalizedText("pmkisan.gov.in પર 'New Farmer Registration' અથવા 'Know Your Status' ખોલો.", "pmkisan.gov.in पर 'New Farmer Registration' या 'Know Your Status' खोलें।"),
                LocalizedText("આધાર OTP દ્વારા e-KYC પૂર્ણ કરો.", "आधार OTP द्वारा e-KYC पूरा करें।")
            ),
            schemeScope = SchemeScope.CENTRAL,
            isDemo = true
        )
    )

    // ==========================================
    // 7. STEP-BY-STEP FORM HELP GUIDES (Gujarati & Hindi)
    // ==========================================
    val formHelpGuides: List<FormHelpGuide> = listOf(
        FormHelpGuide(
            id = "guide_ojas",
            title = LocalizedText(
                gu = "OJAS પર સરકારી નોકરીનું ફોર્મ કેવી રીતે ભરવું?",
                hi = "OJAS पर सरकारी नौकरी का फॉर्म कैसे भरें?"
            ),
            subtitle = LocalizedText(
                gu = "OTR રજિસ્ટ્રેશન, ફોટો-સહી સાઇઝ, અરજી કન્ફર્મેશન અને ફી ભરવાની સંપૂર્ણ રીત.",
                hi = "OTR रजिस्ट्रेशन, फोटो-हस्ताक्षर साइज़, आवेदन कन्फर्मेशन और शुल्क जमा करने की पूरी प्रक्रिया।"
            ),
            portalName = "ojas.gujarat.gov.in",
            officialUrl = "https://ojas.gujarat.gov.in",
            publishedDate = "2026-11-20",
            estimatedMinutes = 10,
            documentChecklist = listOf(
                ChecklistDocument(
                    id = "ojas_doc_1",
                    label = LocalizedText("તાજો પાસપોર્ટ ફોટો (JPG, 15 KB થી ઓછો)", "ताजा पासपोर्ट फोटो (JPG, 15 KB से कम)")
                ),
                ChecklistDocument(
                    id = "ojas_doc_2",
                    label = LocalizedText("સફેદ કાગળ પર કાળી/વાદળી સહી (JPG, 15 KB થી ઓછી)", "सफेद कागज़ पर काली/नीली स्याही से हस्ताक्षर (15 KB से कम)")
                ),
                ChecklistDocument(
                    id = "ojas_doc_3",
                    label = LocalizedText("ધોરણ 10 / 12 / સ્નાતક માર્કશીટ (ટકાવારી અને સીટ નંબર માટે)", "10वीं / 12वीं / स्नातक मार्कशीट (प्रतिशत व सीट नंबर के लिए)")
                ),
                ChecklistDocument(
                    id = "ojas_doc_4",
                    label = LocalizedText("જાતિ / નોન-ક્રિમીલેયર / EWS પ્રમાણપત્ર નંબર અને તારીખ", "जाति / नॉन-क्रीमीलेयर / EWS प्रमाण-पत्र संख्या और तिथि")
                )
            ),
            steps = listOf(
                FormStep(
                    stepNumber = 1,
                    title = LocalizedText("અધિકૃત OJAS વેબસાઇટ ખોલો", "आधिकारिक OJAS वेबसाइट खोलें"),
                    instruction = LocalizedText(
                        gu = "તમારા મોબાઇલ કે કોમ્પ્યુટર બ્રાઉઝરમાં ojas.gujarat.gov.in ખોલો. જો તમે પહેલીવાર અરજી કરો છો તો Registration -> Apply માં જઈ OTR (One Time Registration) બનાવો.",
                        hi = "अपने मोबाइल या कंप्यूटर ब्राउज़र में ojas.gujarat.gov.in खोलें। पहली बार आवेदन कर रहे हैं तो Registration -> Apply में जाकर OTR बनाएं।"
                    )
                ),
                FormStep(
                    stepNumber = 2,
                    title = LocalizedText("ચાલુ જાહેરાત પસંદ કરો", "वर्तमान विज्ञापन चुनें"),
                    instruction = LocalizedText(
                        gu = "મેનુમાંથી 'Online Application' -> 'Apply' પર ક્લિક કરો. સંબંધિત બોર્ડ (GSSSB / GPSSB / Police) પસંદ કરી જાહેરાતની વિગત (PDF) વાંચ્યા પછી Apply દબાવો.",
                        hi = "मेनू से 'Online Application' -> 'Apply' पर क्लिक करें। संबंधित बोर्ड (GSSSB / GPSSB / Police) चुनें और विज्ञापन PDF पढ़कर Apply दबाएं।"
                    )
                ),
                FormStep(
                    stepNumber = 3,
                    title = LocalizedText("વ્યક્તિગત અને શૈક્ષણિક વિગતો ભરો", "व्यक्तिगत और शैक्षणिक विवरण भरें"),
                    instruction = LocalizedText(
                        gu = "OTR નંબર અને જન્મ તારીખ નાખતા મોટાભાગની વિગતો આપોઆપ ભરાઈ જશે. ધોરણ 10/12/ડિગ્રીની ટકાવારી, બોર્ડ/યુનિવર્સિટી અને પાસિંગ વર્ષ ધ્યાનથી ચકાસી Save કરો.",
                        hi = "OTR नंबर और जन्म तिथि डालने पर अधिकतर जानकारी स्वतः भर जाएगी। 10वीं/12वीं/डिग्री के प्रतिशत, बोर्ड/यूनिवर्सिटी और उत्तीर्ण वर्ष जांचकर Save करें।"
                    )
                ),
                FormStep(
                    stepNumber = 4,
                    title = LocalizedText("ફોટો-સહી અપલોડ અને Confirm કરો", "फोटो-हस्ताक्षर अपलोड और Confirm करें"),
                    instruction = LocalizedText(
                        gu = "'Upload Photo/Signature' માં 15 KB થી નાની ફાઇલ અપલોડ કરો. ત્યારબાદ 'Confirm Application' ફરજિયાત કરો — Confirm કર્યા વગર અરજી માન્ય ગણાતી નથી!",
                        hi = "'Upload Photo/Signature' में 15 KB से कम की फाइल अपलोड करें। फिर 'Confirm Application' अनिवार्य रूप से करें — बिना Confirm किए आवेदन मान्य नहीं होता!"
                    )
                ),
                FormStep(
                    stepNumber = 5,
                    title = LocalizedText("ફી ભરો અને પ્રિન્ટ સાચવો", "शुल्क जमा करें और प्रिंट सेव करें"),
                    instruction = LocalizedText(
                        gu = "જો લાગુ પડતી હોય તો ઓનલાઈન (UPI/Card/NetBanking) ફી ભરો. અંતે 'Print Application' પરથી PDF ડાઉનલોડ કરી કન્ફર્મેશન નંબર સાચવી લો.",
                        hi = "यदि लागू हो तो ऑनलाइन (UPI/Card/NetBanking) शुल्क भरें। अंत में 'Print Application' से PDF डाउनलोड करें और कन्फर्मेशन नंबर सुरक्षित रखें।"
                    )
                )
            ),
            commonMistakes = listOf(
                LocalizedText("માત્ર Save કરીને અરજી છોડી દેવી (Confirm કર્યા વગર અરજી અધૂરી ગણાય છે).", "केवल Save करके छोड़ देना (Confirm किए बिना आवेदन अधूरा माना जाता है)।"),
                LocalizedText("નામની સ્પેલિંગ ધોરણ 10 ની માર્કશીટ કરતાં અલગ લખવી.", "नाम की स्पेलिंग 10वीं की मार्कशीट से अलग लिखना।"),
                LocalizedText("અંતિમ તારીખના છેલ્લા કલાકોની રાહ જોવી — સર્વર ધીમું હોઈ શકે છે.", "अंतिम तिथि के आखिरी घंटों का इंतज़ार करना — सर्वर धीमा हो सकता है।")
            ),
            isDemo = true
        ),
        FormHelpGuide(
            id = "guide_digital_gujarat",
            title = LocalizedText(
                gu = "Digital Gujarat સ્કોલરશિપ ફોર્મ સ્ટેપ-બાય-સ્ટેપ માર્ગદર્શિકા",
                hi = "Digital Gujarat स्कॉलरशिप फॉर्म स्टेप-बाय-स्टेप गाइड"
            ),
            subtitle = LocalizedText(
                gu = "ST, SC અને OBC વિદ્યાર્થીઓ માટે પ્રોફાઇલ, આધાર સીડિંગ અને સ્કોલરશિપ ફોર્મ ભરવાની સરળ રીત.",
                hi = "ST, SC और OBC छात्रों के लिए प्रोफ़ाइल, आधार सीडिंग और स्कॉलरशिप फॉर्म भरने की सरल विधि।"
            ),
            portalName = "digitalgujarat.gov.in",
            officialUrl = "https://www.digitalgujarat.gov.in",
            publishedDate = "2026-11-20",
            estimatedMinutes = 12,
            documentChecklist = listOf(
                ChecklistDocument(
                    id = "dg_doc_1",
                    label = LocalizedText("બેંક ખાતું આધાર સાથે DBT લિંક (NPCI Seeded) હોવું જોઈએ", "बैंक खाता आधार के साथ DBT लिंक (NPCI Seeded) होना चाहिए")
                ),
                ChecklistDocument(
                    id = "dg_doc_2",
                    label = LocalizedText("તાજો માન્ય આવકનો દાખલો અને જાતિનો દાખલો", "नवीनतम वैध आय प्रमाण-पत्र और जाति प्रमाण-पत्र")
                ),
                ChecklistDocument(
                    id = "dg_doc_3",
                    label = LocalizedText("કોલેજ/શાળાની ફી રસીદ, આઇ-કાર્ડ અને બોનાફાઇડ", "कॉलेज/स्कूल की फीस रसीद, आई-कार्ड और बोनाफाइड")
                ),
                ChecklistDocument(
                    id = "dg_doc_4",
                    label = LocalizedText("છેલ્લા વર્ષની પાસ માર્કશીટ અને બ્રેક એફિડેવિટ (જો ગેપ હોય તો)", "पिछले वर्ष की पास मार्कशीट और गैप एफिडेविट (यदि गैप हो)")
                )
            ),
            steps = listOf(
                FormStep(
                    stepNumber = 1,
                    title = LocalizedText("Student Login / Registration", "Student Login / Registration"),
                    instruction = LocalizedText(
                        gu = "digitalgujarat.gov.in પર 'Login' -> 'Citizen/Student' પસંદ કરો. નવા વિદ્યાર્થીઓ મોબાઇલ નંબર અને ઇમેઇલથી નવું એકાઉન્ટ બનાવે.",
                        hi = "digitalgujarat.gov.in पर 'Login' -> 'Citizen/Student' चुनें। नए छात्र मोबाइल नंबर और ईमेल से नया खाता बनाएं।"
                    )
                ),
                FormStep(
                    stepNumber = 2,
                    title = LocalizedText("My Profile અને આધાર વેરિફિકેશન", "My Profile और आधार वेरिफिकेशन"),
                    instruction = LocalizedText(
                        gu = "'My Profile' માં જઈ તમારું નામ, આધાર નંબર, સરનામું અને પાસપોર્ટ ફોટો અપડેટ કરો. આધાર મુજબ જ નામ અને જન્મ તારીખ રાખો.",
                        hi = "'My Profile' में जाकर अपना नाम, आधार नंबर, पता और पासपोर्ट फोटो अपडेट करें। नाम और जन्म तिथि आधार के अनुसार ही रखें।"
                    )
                ),
                FormStep(
                    stepNumber = 3,
                    title = LocalizedText("યોગ્ય સ્કોલરશિપ યોજના પસંદ કરો", "सही स्कॉलरशिप योजना चुनें"),
                    instruction = LocalizedText(
                        gu = "Services -> Scholarship Services માં જઈ ચાલુ શૈક્ષણિક વર્ષ પસંદ કરો અને તમારી કેટેગરી (ST / SC / SEBC) ની યોગ્ય યોજના પસંદ કરો.",
                        hi = "Services -> Scholarship Services में जाकर वर्तमान शैक्षणिक वर्ष चुनें और अपनी श्रेणी (ST / SC / SEBC) की उपयुक्त योजना चुनें।"
                    )
                ),
                FormStep(
                    stepNumber = 4,
                    title = LocalizedText("દસ્તાવેજો અપલોડ કરી Final Submit કરો", "दस्तावेज़ अपलोड कर Final Submit करें"),
                    instruction = LocalizedText(
                        gu = "તમામ સ્કેન કરેલા દસ્તાવેજો સ્પષ્ટ વાંચી શકાય તે રીતે PDF/JPG માં અપલોડ કરો. બેંક IFSC અને ખાતા નંબર બે વાર ચકાસી 'Verify Mobile & Final Submit' કરો.",
                        hi = "सभी स्कैन किए गए दस्तावेज़ स्पष्ट PDF/JPG में अपलोड करें। बैंक IFSC और खाता संख्या दो बार जांचकर 'Verify Mobile & Final Submit' करें।"
                    )
                ),
                FormStep(
                    stepNumber = 5,
                    title = LocalizedText("પ્રિન્ટ કોલેજ/શાળામાં જમા કરાવો", "प्रिंट कॉलेज/स्कूल में जमा करें"),
                    instruction = LocalizedText(
                        gu = "સબમિટ કરેલા ફોર્મની પ્રિન્ટ કાઢી જરૂરી ઝેરોક્ષ સાથે તમારી સંસ્થાના સ્કોલરશિપ ક્લાર્કને જમા કરાવો જેથી Institute Verification સમયસર થઈ શકે.",
                        hi = "सबमिट फॉर्म का प्रिंट निकालकर आवश्यक प्रतियों के साथ अपने संस्थान में जमा करें ताकि Institute Verification समय पर हो सके।"
                    )
                )
            ),
            commonMistakes = listOf(
                LocalizedText("બેંક ખાતું આધાર DBT સાથે લિંક ન હોવાથી સહાય અટકી જવી.", "बैंक खाता आधार DBT से लिंक न होने पर सहायता रुक जाना।"),
                LocalizedText("અધૂરી અથવા ઝાંખી માર્કશીટ/આવકના દાખલાની નકલ અપલોડ કરવી.", "अस्पष्ट या धुंधली मार्कशीट/आय प्रमाण-पत्र अपलोड करना।"),
                LocalizedText("ફોર્મ ભર્યા પછી કોલેજમાં વેરિફિકેશન માટે પ્રિન્ટ જમા ન કરાવવી.", "फॉर्म भरने के बाद कॉलेज में सत्यापन के लिए प्रिंट जमा न करना।")
            ),
            isDemo = true
        ),
        FormHelpGuide(
            id = "guide_gcas",
            title = LocalizedText(
                gu = "GCAS કોલેજ એડમિશન (BA / BCom / BSc) ફોર્મ માર્ગદર્શિકા",
                hi = "GCAS कॉलेज एडमिशन (BA / BCom / BSc) फॉर्म गाइड"
            ),
            subtitle = LocalizedText(
                gu = "ધોરણ 12 પછી ગુજરાતની સરકારી યુનિવર્સિટીમાં એડમિશન માટે પ્રોફાઇલ, ચોઇસ ફિલિંગ અને વેરિફિકેશન.",
                hi = "12वीं के बाद गुजरात के सरकारी विश्वविद्यालयों में प्रवेश के लिए प्रोफ़ाइल, चॉइस फिलिंग और वेरिफिकेशन।"
            ),
            portalName = "gcas.gujgov.edu.in",
            officialUrl = "https://gcas.gujgov.edu.in",
            publishedDate = "2026-11-19",
            estimatedMinutes = 10,
            documentChecklist = listOf(
                ChecklistDocument(
                    id = "gcas_doc_1",
                    label = LocalizedText("ધોરણ 12 માર્કશીટ, LC અને ટ્રાયલ સર્ટિફિકેટ", "12वीं मार्कशीट, LC और ट्रायल सर्टिफिकेट")
                ),
                ChecklistDocument(
                    id = "gcas_doc_2",
                    label = LocalizedText("આધાર કાર્ડ અને તાજો પાસપોર્ટ ફોટો", "आधार कार्ड और ताजा पासपोर्ट फोटो")
                ),
                ChecklistDocument(
                    id = "gcas_doc_3",
                    label = LocalizedText("જાતિ / EWS / નોન-ક્રિમીલેયર પ્રમાણપત્ર (લાગુ પડતું હોય તો)", "जाति / EWS / नॉन-क्रीमीलेयर प्रमाण-पत्र (यदि लागू हो)")
                )
            ),
            steps = listOf(
                FormStep(
                    stepNumber = 1,
                    title = LocalizedText("GCAS પોર્ટલ પર રજિસ્ટ્રેશન", "GCAS पोर्टल पर रजिस्ट्रेशन"),
                    instruction = LocalizedText(
                        gu = "gcas.gujgov.edu.in પર 'Apply Now' દબાવી તમારું નામ, મોબાઇલ, ઇમેઇલ અને બોર્ડની વિગતથી રજિસ્ટ્રેશન કરો.",
                        hi = "gcas.gujgov.edu.in पर 'Apply Now' दबाकर नाम, मोबाइल, ईमेल और बोर्ड विवरण से रजिस्ट्रेशन करें।"
                    )
                ),
                FormStep(
                    stepNumber = 2,
                    title = LocalizedText("શૈક્ષણિક ગુણ અને દસ્તાવેજ અપલોડ", "शैक्षणिक अंक और दस्तावेज़ अपलोड"),
                    instruction = LocalizedText(
                        gu = "ધોરણ 10 અને 12 ના વિષયવાર ગુણ સાચા દાખલ કરો અને માર્કશીટ, LC તથા કેટેગરી પ્રમાણપત્ર અપલોડ કરો.",
                        hi = "10वीं और 12वीं के विषयवार अंक सही दर्ज करें तथा मार्कशीट, LC और श्रेणी प्रमाण-पत्र अपलोड करें।"
                    )
                ),
                FormStep(
                    stepNumber = 3,
                    title = LocalizedText("યુનિવર્સિટી અને કોલેજ ચોઇસ ફિલિંગ", "यूनिवर्सिटी और कॉलेज चॉइस फिलिंग"),
                    instruction = LocalizedText(
                        gu = "તમારા પસંદગીના ક્રમમાં (1, 2, 3...) વધુમાં વધુ કોલેજો અને કોર્સ (BA/BCom/BSc) પસંદ કરો જેથી પ્રવેશની તક વધે.",
                        hi = "अपनी पसंद के क्रम (1, 2, 3...) में अधिक से अधिक कॉलेज और कोर्स (BA/BCom/BSc) चुनें ताकि प्रवेश की संभावना बढ़े।"
                    )
                ),
                FormStep(
                    stepNumber = 4,
                    title = LocalizedText("વેરિફિકેશન અને મેરિટ ચેક", "वेरिफिकेशन और मेरिट चेक"),
                    instruction = LocalizedText(
                        gu = "નજીકના વેરિફિકેશન સેન્ટર પર અસલ દસ્તાવેજો ચકાસાવો. મેરિટ રાઉન્ડ જાહેર થાય એટલે લોગિન કરી Offer Letter સ્વીકારો.",
                        hi = "नजदीकी वेरिफिकेशन सेंटर पर मूल दस्तावेज़ जांच करवाएं। मेरिट राउंड घोषित होने पर लॉगिन कर Offer Letter स्वीकार करें।"
                    )
                )
            ),
            commonMistakes = listOf(
                LocalizedText("માત્ર 1-2 કોલેજ પસંદ કરવી (ઓછામાં ઓછી 5-10 પસંદગી રાખવી હિતાવહ છે).", "केवल 1-2 कॉलेज चुनना (कम से कम 5-10 विकल्प चुनना बेहतर होता है)।"),
                LocalizedText("ફોર્મ ભર્યા પછી વેરિફિકેશન સેન્ટર પર ચકાસણી ભૂલી જવી.", "फॉर्म भरने के बाद वेरिफिकेशन सेंटर पर सत्यापन भूल जाना।")
            ),
            isDemo = true
        ),
        FormHelpGuide(
            id = "guide_iti",
            title = LocalizedText(
                gu = "ગુજરાત ITI એડમિશન ઓનલાઈન ફોર્મ ભરવાની રીત",
                hi = "गुजरात ITI एडमिशन ऑनलाइन फॉर्म भरने की विधि"
            ),
            subtitle = LocalizedText(
                gu = "ધોરણ 8 / 10 પછી ITI ટ્રેડ પસંદગી, નજીકની ITI પર કન્ફર્મેશન અને મેરિટ પ્રક્રિયા.",
                hi = "8वीं / 10वीं के बाद ITI ट्रेड चयन, नजदीकी ITI पर कन्फर्मेशन और मेरिट प्रक्रिया।"
            ),
            portalName = "itiadmission.gujarat.gov.in",
            officialUrl = "https://itiadmission.gujarat.gov.in",
            publishedDate = "2026-11-18",
            estimatedMinutes = 8,
            documentChecklist = listOf(
                ChecklistDocument(
                    id = "iti_doc_1",
                    label = LocalizedText("ધોરણ 8 અથવા 10 ની માર્કશીટ અને LC", "8वीं या 10वीं की मार्कशीट और LC")
                ),
                ChecklistDocument(
                    id = "iti_doc_2",
                    label = LocalizedText("આધાર કાર્ડ અને જાતિ પ્રમાણપત્ર (લાગુ પડતું હોય તો)", "आधार कार्ड और जाति प्रमाण-पत्र (यदि लागू हो)")
                )
            ),
            steps = listOf(
                FormStep(
                    stepNumber = 1,
                    title = LocalizedText("ઓનલાઈન ઉમેદવાર રજિસ્ટ્રેશન", "ऑनलाइन उम्मीदवार रजिस्ट्रेशन"),
                    instruction = LocalizedText(
                        gu = "itiadmission.gujarat.gov.in પર નવું ફોર્મ ભરી રજિસ્ટ્રેશન નંબર મેળવો અને નિયત રજિસ્ટ્રેશન ફી ભરો.",
                        hi = "itiadmission.gujarat.gov.in पर नया फॉर्म भरकर रजिस्ट्रेशन नंबर प्राप्त करें और निर्धारित पंजीकरण शुल्क जमा करें।"
                    )
                ),
                FormStep(
                    stepNumber = 2,
                    title = LocalizedText("નજીકની સરકારી ITI પર ફોર્મ કન્ફર્મેશન", "नजदीकी सरकारी ITI पर फॉर्म कन्फर्मेशन"),
                    instruction = LocalizedText(
                        gu = "ઓનલાઈન પ્રિન્ટ અને અસલ દસ્તાવેજો સાથે કોઈપણ નજીકની સરકારી ITI પર જઈ ફોર્મ કન્ફર્મ કરાવી રસીદ લો.",
                        hi = "ऑनलाइन प्रिंट और मूल दस्तावेज़ों के साथ किसी भी नजदीकी सरकारी ITI पर जाकर फॉर्म कन्फर्म करवाएं और रसीद लें।"
                    )
                ),
                FormStep(
                    stepNumber = 3,
                    title = LocalizedText("ટ્રેડ ચોઇસ ફિલિંગ અને મેરિટ", "ट्रेड चॉइस फिलिंग और मेरिट"),
                    instruction = LocalizedText(
                        gu = "લોગિન કરી તમારા પસંદગીના ટ્રેડ (Electrician, Fitter, COPA વગેરે) પસંદ કરી લોક કરો અને મેરિટ રાઉન્ડમાં સીટ કન્ફર્મ કરો.",
                        hi = "लॉगिन कर अपने पसंदीदा ट्रेड (Electrician, Fitter, COPA आदि) चुनकर लॉक करें और मेरिट राउंड में सीट कन्फर्म करें।"
                    )
                )
            ),
            commonMistakes = listOf(
                LocalizedText("ઓનલાઈન ફોર્મ ભર્યા પછી નજીકની ITI પર કન્ફર્મ ન કરાવવું.", "ऑनलाइन फॉर्म भरने के बाद नजदीकी ITI में कन्फर्म न करवाना।")
            ),
            isDemo = true
        )
    )
}
