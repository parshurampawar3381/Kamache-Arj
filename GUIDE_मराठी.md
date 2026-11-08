# संपूर्ण मार्गदर्शक – GitHub, PIN, ॲप सेटअप (सोप्या भाषेत)

## भाग १ – कल्पना समजून घ्या
* **GitHub** = इंटरनेटवरील तुमची फाईल-कपाट (repository). तिथे ॲपची वेबपेज फाईल्स व कामांची यादी ठेवतो.
* **GitHub Pages** = ती फाईल्स मोफत वेबसाईट म्हणून चालवते. लिंक: `https://तुमचे-नाव.github.io/repo-नाव/`
* **APK** = मोबाईलवर इंस्टॉल होणारे छोटे ॲप. ते फक्त वरची लिंक उघडते. म्हणून GitHub वर बदल केला की सर्वांच्या ॲपमध्ये आपोआप दिसतो. APK पुन्हा बनवायचा/इंस्टॉल करायचा नाही.
* **Admin** = तुम्ही. ॲपमध्ये ⚙️ Admin मधून नवीन फॉर्म जोडून "प्रकाशित करा" दाबता; ते GitHub वर जातात.

## भाग २ – GitHub खाते व repository
1. github.com → Sign up (नाव, ईमेल, पासवर्ड). ईमेल verify करा.
2. उजवीकडे वर **+ → New repository**. Name: `vbgramg-forms`. **Public** निवडा. *Create repository*.
3. नवीन repo च्या पानावर **uploading an existing file** लिंक दाबा. झिप extract करून त्यातील `docs`, `android`, `README.md`, `GUIDE_मराठी.md` हे सर्व फोल्डर/फाईल्स ड्रॅग करून टाका. (फोन/कॉम्प्युटर ब्राउझरमध्ये फोल्डर अपलोड जमत नसेल तर कॉम्प्युटर वापरा किंवा GitHub Desktop.) खाली **Commit changes** दाबा.
   * टीप: `docs/pdfs` मध्ये ७ PDF आहेत; सर्व अपलोड झाल्या आहेत हे तपासा.

## भाग ३ – GitHub Pages चालू करा
1. Repo → **Settings** → डावीकडे **Pages**.
2. *Build and deployment* → Source: **Deploy from a branch**. Branch: **main**, फोल्डर: **/docs** → **Save**.
3. १-२ मिनिटांनी वर लिंक दिसेल (`https://…github.io/vbgramg-forms/`). ती मोबाईलवर उघडून ॲप चालतो का पाहा.

## भाग ४ – APK बनवा (एकदाच)
1. Android Studio इंस्टॉल करा (developer.android.com/studio).
2. `android/app/src/main/java/in/vbgramg/forms/MainActivity.java` उघडा. `BASE_URL` मध्ये भाग ३ ची लिंक टाका (शेवटी `/` ठेवा):
   `static final String BASE_URL = "https://तुमचे-नाव.github.io/vbgramg-forms/";`
3. Android Studio → **File → Open** → `android` फोल्डर निवडा. खाली "Gradle sync" पूर्ण होईपर्यंत थांबा (पहिल्यांदा काही मिनिटे).
4. **Build → Build Bundle(s) / APK(s) → Build APK(s)**. तयार झाल्यावर "locate" दाबून `app-debug.apk` मिळवा.
5. तो APK व्हॉट्सॲप/ड्राइव्हने वापरकर्त्यांना द्या; इंस्टॉल करताना "unknown sources" परवानगी द्यावी लागेल. (Play Store साठी signed release APK/AAB लागतो – तो नंतर.)
6. ॲप Android 10 किंवा नंतरच्या फोनवर चालतो.

## भाग ५ – नवीन फॉर्म जोडणे व प्रकाशित करणे (Admin)
1. ॲप उघडा → वर **⚙️ Admin** → PIN (सुरुवातीला `1234`).
2. **नवीन फॉर्म** टॅब: चार प्रश्नांची उत्तरे (कामाचे नाव मराठीत, प्रकार, मराठी फॉर्म आहे का, मजकूर निवडता येतो का) → PDF निवडा → छापायची पाने निवडा → **पुढे** दाबा.
3. **कामे / पाने** टॅब: ते काम उघडा → "जागा" बटण → प्रत्येक रिकाम्या जागेसमोर कोणती माहिती छापायची ते यादीतून निवडा. "तसेच राहू द्या" निवडली तर ती रेषा/ठिपके रिकामेच राहतील. हव्या त्या जागी PDF वर क्लिक करून नवीन जागा देता येते. पान काढणे (✕), वर/खाली (↑↓), दुसऱ्या PDF चे पान जोडणे येथेच.
4. **GitHub token बनवा** (एकदाच): GitHub → उजवीकडे फोटो → Settings → Developer settings → Personal access tokens → **Fine-grained tokens → Generate new token**. Name: `vbgramg`. Expiration: 1 year. *Repository access*: **Only select repositories** → तुमचा repo. *Permissions → Repository permissions → Contents: Read and write*. Generate → दिसणारा token (`github_pat_…`) कॉपी करा (तो पुन्हा दिसत नाही).
5. ॲपमध्ये **प्रकाशित करा** टॅब: GitHub वापरकर्ता नाव, repo नाव (`vbgramg-forms`), branch `main`, फोल्डर `docs/`, token भरा → **🚀 प्रकाशित करा**. "✅ प्रकाशित झाले" दिसल्यावर १-२ मिनिटांत सर्व वापरकर्त्यांना नवीन फॉर्म दिसतो.
6. token फक्त तुमच्या फोनवर साठवला जातो. तो कोणालाही देऊ नका. हरवला/लीक झाला तर GitHub मध्ये तो Revoke करून नवीन बनवा.

## भाग ६ – Admin PIN बदलणे
**सोपी पद्धत (ॲपमधून):** Admin → प्रकाशित करा टॅब → GitHub माहिती/token भरलेली ठेवा → "Admin PIN बदला" मध्ये नवा PIN → **PIN बदला**. १-२ मिनिटांत सर्वत्र लागू.
**हाताने पद्धत:** नवीन PIN चा SHA-256 hash काढा (उदा. emn178.github.io/online-tools/sha256.html). GitHub मध्ये `docs/data/config.json` उघडा → पेन्सिल (Edit) → `pinHash` बदला → Commit.
PIN विसरलात तर वरची हाताने पद्धत वापरून नवीन hash टाका.

## भाग ७ – नंतर ॲपचे अपडेट
* फॉर्म/कामे बदल = Admin मधून प्रकाशित करा. (APK नाही बदलावा.)
* ॲपचे स्वरूप/सुविधा बदल (नवीन आवृत्ती मी देईन) = `docs/index.html` GitHub वर बदला (Upload/replace). सर्वांना आपोआप मिळेल.
* ॲपचे नाव/आयकॉन बदलायचे असेल तरच नवा APK.

## समस्या निवारण
* ॲप उघडल्यावर "डेटा लोड झाला नाही": इंटरनेट तपासा; GitHub Pages चालू आहे का (भाग ३) पाहा; `BASE_URL` बरोबर आहे का.
* प्रकाशित केल्यावर बदल दिसत नाही: २-३ मिनिटे थांबा; ॲप बंद करून पुन्हा उघडा.
* "❌ 401/403": token चुकीचा/परवानगी कमी (Contents: Read and write) किंवा repo नाव चुकीचे.
* "❌ 404": वापरकर्ता/repo नाव किंवा `docs/` फोल्डर नाव तपासा.
