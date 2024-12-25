const apiUrl = "http://localhost:8080/api/recommendations";

// Fetch Top-Rated Products
document.getElementById("fetchTopRated").addEventListener("click", async () => {
    const response = await fetch(`${apiUrl}/top-rated`);
    const products = await response.json();
    displayProducts("topRatedProducts", products);
});

// Fetch Personalized Recommendations
document.getElementById("fetchPersonalized").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInput").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/personal/${userId}`);
    const products = await response.json();
    displayProducts("personalRecommendations", products);
});

// Fetch Recommendations Based on Ratings
document.getElementById("fetchBasedOnRatings").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInputRatings").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/based-on-ratings/${userId}`);
    const products = await response.json();
    displayProducts("recommendationsBasedOnRatings", products);
});

// Display Products
function displayProducts(containerId, products) {
    const container = document.getElementById(containerId);
    container.innerHTML = ""; // Clear previous results
    if (products.length === 0) {
        container.innerHTML = "<li>No products found.</li>";
        return;
    }
    products.forEach(product => {
        const li = document.createElement("li");
        li.textContent = `${product.name} (Rating: ${product.rating})`;
        container.appendChild(li);
    });
}
