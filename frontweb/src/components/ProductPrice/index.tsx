import './styles.css';

type Props = {
    price: number;
}

const ProductPrice = ( {price} : Props) => {
    return(
        <div className='product-preice-container'>
            <span>R$</span>
            <h3>{price}</h3>
        </div>
    );
}

export default ProductPrice;