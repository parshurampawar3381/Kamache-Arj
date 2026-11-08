const V='vbg-v1',CDN=/cdnjs\.cloudflare\.com|fonts\.(googleapis|gstatic)\.com/;
self.addEventListener('install',e=>self.skipWaiting());
self.addEventListener('activate',e=>e.waitUntil(clients.claim()));
self.addEventListener('fetch',e=>{
 const r=e.request;if(r.method!=='GET')return;const u=new URL(r.url);
 if(CDN.test(u.host)||u.pathname.includes('/pdfs/')){ // जुन्या कॅशमधून आधी
  e.respondWith(caches.open(V).then(c=>c.match(r).then(m=>m||fetch(r).then(x=>{if(x.ok||x.type==='opaque')c.put(r,x.clone());return x}))));return}
 if(u.origin===location.origin){ // आधी नेटवर्क (नेहमी ताजे), नसल्यास कॅश
  e.respondWith(fetch(r).then(x=>{const k=r.url.split('?')[0];caches.open(V).then(c=>c.put(k,x.clone()));return x}).catch(()=>caches.match(r.url.split('?')[0]).then(m=>m||caches.match(r))))}
});
