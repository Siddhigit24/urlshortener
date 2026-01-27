function shortenUrl() {
    const longUrl = document.getElementById("longUrl").value;
    const resultDiv = document.getElementById("result");

    if (!longUrl) {
        alert("Please enter a URL");
        return;
    }

    resultDiv.innerText = "Shortening... ⏳";

    fetch(`/api/shorten?url=${encodeURIComponent(longUrl)}`)
        .then(response => response.text())
        .then(code => {
            const shortUrl = `${window.location.origin}/api/${code}`;

            resultDiv.innerHTML = `
                <div>✅ Short URL created:</div>
                <a href="${shortUrl}" target="_blank">${shortUrl}</a>
                <br>
                <button class="copy-btn" onclick="copyToClipboard('${shortUrl}')">
                    📋 Copy URL
                </button>
            `;
        })
        .catch(() => {
            resultDiv.innerText = "❌ Something went wrong!";
        });
}

function copyToClipboard(text) {
    navigator.clipboard.writeText(text);
    alert("Short URL copied!");
}

function clearAll() {
    document.getElementById("longUrl").value = "";
    document.getElementById("result").innerHTML = "";
}
