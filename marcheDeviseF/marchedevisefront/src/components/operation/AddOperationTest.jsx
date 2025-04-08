import React, { useState, useEffect } from "react";
import * as Dialog from "@radix-ui/react-dialog";
// import { Dialog } from "radix-ui";
import { Cross2Icon } from "@radix-ui/react-icons";
import "./AddOperation.css";
import { addOperation } from '../../services/operation/operation'; // Import the addOperation service


const AddOperationComponent = ({ selectedDevise, openDialog, onClose }) => {

    const { id, symbol } = selectedDevise || {};  // Ensure selectedDevise is valid
    const [opnum, setOpnum] = useState(10); // Default value for montant
    const [operationType, setOperationType] = useState('EMPRUNT'); // Default operation type
    const [montant, setMontant] = useState(10); // Default value for montant
    const [dateEcheance, setDateEcheance] = useState(''); // State for dateEcheance
    const [deviseH, setDeviseH] = useState(''); // State for devise
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    // useEffect(() => {
    //     // You could fetch data based on selectedDevise if needed
    //     console.log('Selected Devise:', selectedDevise); // Debugging: Check if selectedDevise is passed properly
    //   }, [selectedDevise]);


      useEffect(() => {
        if (selectedDevise) {
            console.log(id);
            console.log("normalement id taa selectedDevise yodhhor")

            setDeviseH(id); // Automatically set the deviseH state to the ID of selectedDevise


        }
    }, [selectedDevise]); // This will trigger when selectedDevise changes


    const increment = (step) => {
        setMontant((prevMontant) => Math.max(0, prevMontant + step)); // Prevent negative values
    };
    // Enhanced handleChange function
    const handleChange = (event) => {
        const newValue = parseFloat(event.target.value);
        if (!isNaN(newValue)) {
             //Check if the value is a valid number
             				if (parseFloat(newValue) > 10) {
					setError("");
				} else {
					setError("Amount must be more than 10.");
				}
            // Update montant and handle error
            setMontant(Math.max(0, newValue)); // Set montant and prevent negatives
//            setError(newValue <= 10 ? "Quantity must be more than 10." : ""); // Set error based on the value
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setSuccessMessage('');

        const operationData = {
            opnum,
            deviseH: {
                id: parseInt(selectedDevise.id) // <-- convert to number, backend expects a numeric ID
            },
            montant,
            type: operationType,
            dateEcheance,
        };

        try {
            // Call the addOperation API service
            const result = await addOperation(operationData);
            setSuccessMessage('Operation added successfully!');
            console.log('Added operation:', result);
            
            // Optionally reset the form fields after successful submission
             //setOpnum('');
             //setMontant(10);
             //setDeviseH('');
             //console.log(deviseH);
            // setDateEcheance('');
            // setDeviseH(id);
            // console.log(deviseH);

        } catch (error) {
            console.error('Error during operation submission:', error);
            setError('Failed to add operation.'); // Show error message
        }

    };
    const tradeFees = montant * 0.001; // Trade fees is 1% of quantity

    return (
            <div className="addOperation">
                {/* open={true} onOpenChange={(isOpen) => { if (!isOpen) onClose(); }} */}
                <Dialog.Root open={openDialog} onOpenChange={onClose}> {/* Open the dialog when selectedDevise is set */}
                <Dialog.Trigger asChild>
                    <button className="Button violet">Passer une operation1</button>
                </Dialog.Trigger>
                <Dialog.Portal>
                    <Dialog.Overlay className="DialogOverlay"/>
                    <Dialog.Content className="DialogContent">
                        <Dialog.Title className="DialogTitle">conduct a financial transaction</Dialog.Title>
                        <Dialog.Description className="DialogDescription">
                        Add Operation for {symbol}. Click save when you're done. 
                        </Dialog.Description>

                        <form onSubmit={handleSubmit} className="DialogForm">
                            {/* <div>
                                <label>Montant:</label>
                                <button type="button" onClick={() => incrementMontant(-1)}>-</button>
                                <input
                                    type="number"
                                    value={montant}
                                    onChange={(e) => setMontant(Math.max(0, Number(e.target.value)))}
                                    required
                                />
                                <button type="button" onClick={() => incrementMontant(1)}>+</button>
                            </div> */}

                            <div className="choisir-devise">
                            <label htmlFor="numOperation" className="DateLabel">numero d'opération</label>     
                                <input 
                                    type="text"
                                    value={opnum}
                                    onChange={(e) => setOpnum(Number(e.target.value))} // Convert to number
                                    className="devise-input"
                                    required
                                /> 
                            </div>
                            {/* */}
                             {/* <div className="choisir-devise">
                            <label htmlFor="deviseChoisie" className="DateLabel">devise choisie</label>
                                <input 
                                    type="number" /*mbaad hotha text ki tbadel fl back */  }
                                     {/* value={deviseH}
                                    onChange={(e) => setDeviseH(e.target.value)}
                                    placeholder="Devise ID" 
                                    className="devise-input"
                                    required
                                />
                            </div>  */}
                            {/** */}
                            <div className="operation-buttons">
                                <button type="button" onClick={() => setOperationType('EMPRUNT')} className="Borrow-button">Borrow</button>
                                <button type="button" onClick={() => setOperationType('PRET')} className="Lend-button">Lend</button>
                            </div>
                            {/** */}
                            <div className="quantity-selector">
                            <label htmlFor="montant" className="Amount">Amount</label>
                                    <button className="Button-quant" onClick={() => increment(-10)}>-</button>
                                        <input 
                                        type="number"
                                        value={montant}
                                        onChange={handleChange} 
                                        className="quantity-input"
                                        required
                                        />
                                    <button className="Button-quant" onClick={() => increment(10)}>+</button>
                            </div>
                            {/** */}
                            <div className="trade-fees">
                            <label htmlFor="tradefees" className="TradefeesLabel">fees</label>
                                    <span className="fees-amount">{tradeFees.toFixed(2)}</span>
                            </div>
                            {/** */}
                            <div className="Date-Echeance">
                                <label htmlFor="echeanceDate" className="DateLabel">Date Echeance</label>
                                <input 
                                    type="date" 
                                    id="echeanceDate" 
                                    onChange={(e) => setDateEcheance(e.target.value)}
                                    required
                                    className="Input DateInput" 
                                    defaultValue="2025-03-27" 
                                />
                            </div>
                            {/* <div>
                                <label>Date Echeance:</label>
                                <input
                                    type="date"
                                    value={dateEcheance}
                                    onChange={(e) => setDateEcheance(e.target.value)}
                                    required
                                />
                            </div> */}

                            <button type="submit" className="Button green" >Add Operation</button>
                        </form>
                            {/* {error && <p style={{ color: 'red' }}>{error}</p>}
                            {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>} */}
                            <div
                                style={{ display: "flex", marginTop: 5, justifyContent: "flex-end" }}
                            >
                        {/* <Dialog.Close asChild>
                            <button className="Button green">Save changes</button>
                        </Dialog.Close> */}
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


export default AddOperationComponent;