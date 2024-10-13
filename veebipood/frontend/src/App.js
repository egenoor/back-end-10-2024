import './App.css';
import {useState, useEffect} from 'react';

function App() {
    const [products, setProducts] = useState([])

    useEffect(() => {
        fetch("http://localhost:8080/products")
            .then(res => res.json())
            .then(json => setProducts(json));
    }, []);

    const add = () => {
        fetch("http://localhost:8080/add-product?name=coca&price=2")
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
