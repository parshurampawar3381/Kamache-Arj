# VB-G RAM G अर्ज सहाय्यक – GitHub + APK मार्गदर्शक

## १) GitHub वर ठेवा (एकदाच)
1. github.com वर नवीन **public** repository बनवा (उदा. `vbgramg-forms`).
2. या झिप मधील सर्व फोल्डर (`docs`, `android`, `README.md`) त्या repo मध्ये अपलोड करा.
3. Repo → **Settings → Pages → Build and deployment**: Source = *Deploy from a branch*, Branch = `main`, Folder = `/docs` → Save.
4. १-२ मिनिटांनी लिंक मिळेल: `https://<तुमचे-नाव>.github.io/<repo>/` – ती उघडून ॲप चालतो का पहा.

## २) APK बनवा (Android Studio)
1. `android/app/src/main/java/in/vbgramg/forms/MainActivity.java` उघडा. वरच्या ओळीतील `BASE_URL` मध्ये वरची लिंक टाका (शेवटी `/` ठेवा).
2. Android Studio → *Open* → `android` फोल्डर निवडा → Gradle sync होऊ द्या.
3. *Build → Build Bundle(s)/APK(s) → Build APK(s)*. तयार APK वापरकर्त्यांना द्या.
4. **हा APK फक्त एकदा इंस्टॉल करायचा.** ॲप तुमच्या GitHub लिंकवरून चालतो, त्यामुळे नंतर GitHub वर काही बदलले की वापरकर्त्याच्या मोबाईलवर आपोआप येते. इंटरनेट नसताना शेवटचा लोड झालेला भाग चालतो. APK पुन्हा बनवण्याची गरज फक्त ॲपचे नाव/आयकॉन बदलायचे असेल तरच.

## ३) Admin ने नवीन फॉर्म जोडणे व प्रकाशित करणे
1. ॲपमध्ये वर **⚙️ Admin** → PIN (डिफॉल्ट `1234`).
2. **नवीन फॉर्म** टॅब: प्रश्नांची उत्तरे द्या (कामाचे नाव, प्रकार, मराठी आहे का…) → PDF निवडा → पाने निवडा → जागा ओळखल्या जातात. **कामे / पाने** टॅबमध्ये प्रत्येक जागेसमोर कोणती माहिती छापायची ते तपासा; पाने वाढवा/काढा/क्रम बदला.
3. **प्रकाशित करा** टॅब: GitHub user, repo नाव, branch (`main`), फोल्डर (`docs/`) आणि Personal Access Token भरा → 🚀 प्रकाशित करा.
   * Token: GitHub → Settings → Developer settings → Personal access tokens → *Fine-grained* → फक्त तुमचा repo → Permissions: **Contents = Read and write**. हा token फक्त Admin च्या मोबाईलवर राहतो; इतरांना देऊ नका.
4. प्रकाशित झाल्यावर सर्व वापरकर्त्यांना १-२ मिनिटांत नवीन फॉर्म दिसतो.

## ४) Admin PIN बदलणे
नवीन PIN चा SHA-256 hash काढा (उदा. https://emn178.github.io/online-tools/sha256.html) आणि `docs/data/config.json` मधील `pinHash` बदला.
(टीप: हे सोपे संरक्षण आहे; GitHub token खरे संरक्षण देतो – token फक्त Admin कडेच ठेवा.)

## लोगो (ॲप आयकॉन)
ॲपचा आयकॉन "जीआरएस संघटना तालुका सातारा" गोल लोगो आहे (`android/app/src/main/res/mipmap-*`). लोगो बदलायचा असेल तर त्या फोल्डरमधील `ic_launcher.png`, `ic_launcher_round.png` आणि `ic_launcher_foreground.png` फाईल्स त्याच नावाने बदला; वेबसाठी `docs/logo.png` व `docs/logo192.png`. ॲपचे नाव तेच ठेवले आहे.
