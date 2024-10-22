import { useEffect, useState } from 'react';
import './App.css';

function App() {
    const [products, setProducts] = useState([])

    useEffect(() => {
        fetch("http://localhost:8080/products")
            .then(res => res.json())
            .then(json => setProducts(json.content));
    }, []);

    const add = () => {
        fetch("http://localhost:8080/add-product?name=coca&price=2",
          // {method: "POST", body: JSON.stringify(), headers: {"Content-Type": "application/json"}}
        )
            .then(res => res.json())
            .then(json => setProducts(json))
    }

  return (
    <div className="App">
      {products.map(product => <div>{product.name}</div>)}

      <button onClick={add}>Add</button>
    </div>
  );
}

export default App;
