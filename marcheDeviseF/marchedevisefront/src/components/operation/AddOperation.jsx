import React, { useState } from "react";
import * as Dialog from "@radix-ui/react-dialog";
// import { Dialog } from "radix-ui";
import { Cross2Icon } from "@radix-ui/react-icons";
import "./AddOperation.css";


const DialogDemo = () => {

    const [quantity, setQuantity] = useState(10.000); // Default value is 10,000



    const increment = (amount) => {
        setQuantity((prevQuantity) => prevQuantity + amount);
		// if (newQuantity > 10) {
        //     setQuantity(newQuantity);
        //     setError(""); // Clear error if input is valid
        // } else {
        //     setError("Quantity must be more than 10.");
        // }
    };    
	const handleChange = (event) => {
        const newValue = parseFloat(event.target.value);
        if (!isNaN(newValue)) { // Check if the value is a valid number
            setQuantity(newValue);
        }
		        // //Reset error if input is valid
				// if (parseFloat(newValue) > 10) {
				// 	setError("");
				// } else {
				// 	setError("Quantity must be more than 10.");
				// }
    };

        // Step 3: Calculate trade fees based on quantity
        const tradeFees = quantity * 0.01; // Trade fees is 1% of quantity
    return(
        <div className="addOperation">

	<Dialog.Root>
		<Dialog.Trigger asChild>
			<button className="Button violet">Passer une operation</button>
		</Dialog.Trigger>
		<Dialog.Portal>
			<Dialog.Overlay className="DialogOverlay"/>
			<Dialog.Content className="DialogContent">
				<Dialog.Title className="DialogTitle">Passer une opération</Dialog.Title>
				<Dialog.Description className="DialogDescription">
					Passer une opération. Click save when you're done.
				</Dialog.Description>
				<div className="Devisech">
						devise choisie
				</div>
				<div className="operation-buttons">
                <button className="Borrow-button">Borrow</button>
                <button className="Lend-button">Lend</button>
                </div>
                <div className="Date Echeance">
                    <label htmlFor="echeanceDate" className="DateLabel">Date Echeance</label>
                    <input 
                        type="date" 
                        id="echeanceDate" 
                        className="Input DateInput" 
                        defaultValue="2025-03-27" 
                    />
                </div>

                <div className="quantity-selector">
                <label htmlFor="echeanceDate" className="DateLabel">Quantity</label>
                        <button className="Button-quant" onClick={() => increment(-10)}>-</button>
							<input 
								type="number" 
								value={quantity} 
								onChange={handleChange} 
								className="quantity-input"
							/>
                        <button className="Button-quant" onClick={() => increment(10)}>+</button>
                </div>

                <div className="trade-fees">
                <label htmlFor="tradefees" className="TradefeesLabel">fees</label>
                        <span className="fees-amount">{tradeFees.toFixed(2)}</span>
                </div>
				<fieldset className="Fieldset">
					<label className="Label" htmlFor="username">
						Username
					</label>
					<input className="Input" id="username" defaultValue="@peduarte" />
				</fieldset>
				<div
					style={{ display: "flex", marginTop: 25, justifyContent: "flex-end" }}
				>
					<Dialog.Close asChild>
						<button className="Button green">Save changes</button>
					</Dialog.Close>
				</div>
				<Dialog.Close asChild>
					<button className="IconButton" aria-label="Close">
						<Cross2Icon />
					</button>
				</Dialog.Close>
			</Dialog.Content>
		</Dialog.Portal>
	</Dialog.Root>
    </div>

);
};
export default DialogDemo;
