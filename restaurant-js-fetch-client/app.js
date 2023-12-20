let reloadProductsButton = document.getElementById('reloadProducts');

reloadProductsButton.addEventListener('click', reloadProducts)

function reloadProducts() {

  let productsContainer = document.getElementById('products-container');
  productsContainer.innerHTML = ''

  fetch('http://localhost:8080/api/products')
    .then(response => response.json())
    .then(json => json.forEach(products => {

      let productsRow = document.createElement('tr')

      let nameCol = document.createElement('td')
      let descriptionCol = document.createElement('td')

      nameCol.textContent = products.name
      descriptionCol.textContent = products.description

      productsRow.appendChild(nameCol)
      productsRow.appendChild(descriptionCol)

      productsContainer.append(productsRow)
    }))
}
