
import React, { useState, useEffect } from "react";
import * as Dialog from "@radix-ui/react-dialog";
// import { Dialog } from "radix-ui";
import { Cross2Icon } from "@radix-ui/react-icons";
import "./AddOperation.css";
import { addOperation } from '../../services/operation/operation'; // Import the addOperation service
import { calculateMaturity } from "../../services/operation/operation";
import { color } from "chart.js/helpers";
const AddOperationComponent = ({ selectedDevise, openDialog, onClose }) => {

    const { id, label, symbol, borrow, lend, colorBorrow, colorLend } = selectedDevise || {};  // Ensure selectedDevise is valid
    const [operationType, setOperationType] = useState('BORROW'); // Default operation type
    const [amount, setAmount] = useState(100); // Default value for amount
    const [maturityDate, setMaturityDate] = useState(''); // State for maturityDate
    const [intrestRate, setIntrestRate] = useState(''); // State for dateEcheance
    const [intrestType, setIntrestType] = useState('');
    const [edited, setEdited] = useState(false);
    const [maturityRate, setMaturityRate] = useState(null);
    const [maturityAmount, setMaturityAmount] = useState(null);
    const [givedAmount, setGivedAmount] = useState(null);
    const [result, setCalculatedResult] = useState(null); // Define the state here
    const [currency, setCurrency] = useState('');
    const [error, setError] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

      useEffect(() => {
        if (selectedDevise) {
            console.log(id);
            console.log(label);
            console.log(symbol);
            console.log(borrow);
            console.log(lend);
            console.log("colorBorrow ", colorBorrow);
            console.log("colorLend ", colorLend);

            setCurrency(selectedDevise.label); // Automatically set the currency label
            if (operationType === 'BORROW') {
                setIntrestRate(selectedDevise.borrow);
            } else if (operationType === 'LEND') {
                setIntrestRate(selectedDevise.lend);
            }
        }
    }, [selectedDevise,  operationType]); // This will trigger when selectedDevise changes


/////fazet l calcul


    const increment = (step) => {
        setAmount((prevAmount) => Math.max(0, prevAmount + step)); // Prevent negative values
    };

    const handleChange = (field, value) => {
        if (field === 'amount') setAmount(value);
        if (field === 'intrestRate') setIntrestRate(value);
        if (field === 'intrestType') setIntrestType(value);
        if (field === 'maturityDate') setMaturityDate(value);
    };


    const handleChangemont = (event) => {
        const newValue = parseFloat(event.target.value);
        if (!isNaN(newValue)) {
            setError(newValue <= 10 ? "Quantity must be more than 10." : "");
            setAmount(Math.max(0, newValue));
            setEdited(true);
        }
    };

    useEffect(() => {
        const allFilled = amount && intrestRate && intrestType && label && maturityDate;
    
        const fetchCalculatedData = async (inputData) => {
            if (allFilled && edited) {
                try {
                    const result = await calculateMaturity(inputData);
                    setCalculatedResult(result);
                } catch (err) {
                    console.error("Calculation failed", err);
                }
            }
        };
    
        const formattedDate = new Date(maturityDate);
        let isoDate = ""; // Declare isoDate outside the if-else blocks
    
        // Check if the date is valid before proceeding
        if (isNaN(formattedDate.getTime())) {
            setError("Invalid date format");
        } else {
            isoDate = formattedDate.toISOString().split("T")[0] + "T00:00:00"; // Assign isoDate if the date is valid
            console.log("Valid ISO Date:", isoDate);
        }
    
        if (isoDate) {
            console.log("Sending input to calculateMaturity:", {
                amount,
                intrestRate,
                intrestType,
                label,
                maturityDNate: isoDate,
            });
    
            fetchCalculatedData({
                amount,
                intrestRate,
                intrestType,
                label,
                maturityDate: isoDate,
            });
        }
    
    }, [amount, intrestRate, intrestType, maturityDate, label, edited]);
    
    
///
const maturityRate1 = 1;
const maturityAmount1 = 6;
const givedAmount1 = 5;

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setSuccessMessage('');

        const isoDate = new Date(maturityDate).toISOString().split("T")[0] + "T00:00:00";
        console.log("Maturity Date (maturityDate) before sending:", maturityDate);

        console.log("Maturity Date (maturityDate) before sending:", isoDate);

        const operationData = {
            type: operationType,
            amount: amount,
            maturityDate: isoDate,
            intrestType,
            intrestRate,
            currency: {
                label: selectedDevise.label // only sending the label (e.g., "US Dollar")
            },
        };

        try {
            // Call the addOperation API service
            const result = await addOperation(operationData);
            setSuccessMessage('Operation added successfully!');
            console.log('Added operation:', result);

        } catch (error) {
            console.error('Error during operation submission:', error);
            setError('Failed to add operation.'); // Show error message
        }

    };

    return (
            <div className="addOperation">
                <Dialog.Root open={openDialog} onOpenChange={onClose}> 
                <Dialog.Trigger asChild>
                    <button className="Button violet">Passer une operation1</button>
                </Dialog.Trigger>
                <Dialog.Portal>
                    <Dialog.Overlay className="DialogOverlay"/>
                    <Dialog.Content className="DialogContent">
                        <Dialog.Title className="DialogTitle">Effectuer une opération financière</Dialog.Title>
                        <Dialog.Description className="DialogDescription">
                        Effectuer une opération financiére sur la devise {symbol}.
                        </Dialog.Description>

                        <form onSubmit={handleSubmit} className="DialogForm">
                            <div className="flex-container">
                            <div className="flex-child form-middle">

                            <div className="operation-buttons">
                                <button type="button" onClick={() => setOperationType('BORROW')} className="Borrow-button"  style={{ color: colorBorrow }}>Emprunt {borrow}</button>
                                <button type="button" onClick={() => setOperationType('LEND')} className="Lend-button" style={{ color: colorLend }} >Prêt {lend}</button>
                            </div>
                            <div className="intrestType-choice">
                            <label htmlFor="intrestType" className="intrestTypeLabel">Type d'intérêts</label>     
                            <select
                                id="intrestType"
                                value={intrestType}
                                onChange={(e) => handleChange("intrestType", e.target.value)}
                                className="select-intrestType"
                                required
                            >
                                <option value="PRECOUNT">Précompté</option>
                                <option value="POSTCOUNT">Postcompté</option>
                            </select>
                            </div>
                            <div className="intrestRate-choice">
                            <label htmlFor="intrestRate" className="intrestRateLabel">Taux d'intérêts</label>     
                                <input 
                                    type="number"
                                    value={intrestRate}
                                    onChange={(e) => handleChange("intrestRate", Number(e.target.value))}
                                    className="intrestRate-input"
                                    required
                                /> 
                            </div>
                            <div className="amount-choice">
                            <label htmlFor="amount" className="AmountLabel">Montant</label>
                                    <button type="button" className="Button-quant" onClick={() => increment(-10)}>-</button>
                                        <input 
                                        type="number"
                                        value={amount}
                                        onChange={handleChangemont} 
                                        className="quantity-input"
                                        required
                                        />
                                    <button type="button" className="Button-quant" onClick={() => increment(10)}>+</button>
                            </div>
                            <div className="Date-Echeance">
                                <label htmlFor="echeanceDate" className="DateLabel">Date Echeance</label>
                                <input 
                                    type="date" 
                                    id="echeanceDate" 
                                    onChange={(e) => handleChange("maturityDate", e.target.value)}
                                    required
                                    className="Input DateInput" 
                                    defaultValue="2025-03-27" 
                                />
                            </div>
                            </div>
                            <div className="flex-child form-right">
                            <div className="maturityCalcul-output">
                            <label htmlFor="maturityRate" className="maturitycalcul-outputLabel">Taux d'intérêts d'échénace  </label>
                                    <span className="maturityRate-shown">{result?.maturityRate.toFixed(4) ?? '-'}</span>
                            </div> 
                            <div className="maturityCalcul-output">
                            <label htmlFor="maturityAmount" className="maturitycalcul-outputLabel">Montant d'échéance </label>
                                    <span className="maturityAmount-shown">{result?.maturityAmount.toFixed(4) ?? '-'}</span>
                            </div> 
                            <div className="maturityCalcul-output">
                            <label htmlFor="givedAmount" className="maturitycalcul-outputLabel">Montant {operationType === 'BORROW' ? 'emprunté (reçu)' : operationType === 'LEND' ? 'prêté (donné)' : ''} </label>
                                    <span className="givenAmount-shown">{result?.givedAmount.toFixed(4) ?? '-'}</span>
                            </div>
                            </div>
                            </div>


                            {/* <div className="calculated-data">
                                <p>Maturity Rate: {maturityRate}</p>
                                <p>Maturity Amount: {maturityAmount}</p>
                                <p>Gived Amount: {givedAmount}</p>
                            </div> */}

                            <button type="submit" className="OperationSub-button" >Add Operation</button>
                        </form>
                            <div
                                style={{ display: "flex", marginTop: 5, justifyContent: "flex-end" }}
                            >

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
    
    
    

// import React, { useState, useEffect } from "react";
// import * as Dialog from "@radix-ui/react-dialog";
// // import { Dialog } from "radix-ui";
// import { Cross2Icon } from "@radix-ui/react-icons";
// import "./AddOperation.css";
// import { addOperation } from '../../services/operation/operation'; // Import the addOperation service


// const AddOperationComponent = ({ selectedDevise, openDialog, onClose }) => {

//     const { id, label, symbol, borrow, lend } = selectedDevise || {};  // Ensure selectedDevise is valid
// //    const [opnum, setOpnum] = useState(10); // Default value for montant
//     const [operationType, setOperationType] = useState('EMPRUNT'); // Default operation type
//     const [montant, setMontant] = useState(100); // Default value for montant
//     const [dateEcheance, setDateEcheance] = useState(''); // State for dateEcheance
//     const [intrestRate, setIntrestRate] = useState(''); // State for dateEcheance
//     const [intrestType, setIntrestType] = useState('');
//         /*() => {
//         return selectedDevise ? selectedDevise.borrow : '';
//     });*/
//     const [currency, setCurrency] = useState('');
//     //const [deviseH, setDeviseH] = useState(''); // State for devise
//     const [error, setError] = useState('');
//     const [successMessage, setSuccessMessage] = useState('');

//     // useEffect(() => {
//     //     // You could fetch data based on selectedDevise if needed
//     //     console.log('Selected Devise:', selectedDevise); // Debugging: Check if selectedDevise is passed properly
//     //   }, [selectedDevise]);


//       useEffect(() => {
//         if (selectedDevise) {
//             console.log(id);
//             console.log("normalement id taa selectedDevise yodhhor")
//             console.log(label);
//             console.log("normalement label taa selectedDevise yodhhor")
//             console.log(symbol);
//             console.log("normalement symbol taa selectedDevise yodhhor")
//             console.log(borrow);
//             console.log("norma.lement  borrow taa selectedDevise yodhhor")
//             console.log(lend);
//             console.log("normalement id lend selectedDevise yodhhor")
//             //setDeviseH(id); // Automatically set the deviseH state to the ID of selectedDevise
//             setCurrency(selectedDevise.label); // Automatically set the currency label
//             //setIntrestType(selectedDevise.borrow); // Default interest type (can be adjusted based on your logic)

//             // Set default interest rate depending on operation type
//             if (operationType === 'BORROW') {
//                 setIntrestRate(selectedDevise.borrow);
//             } else if (operationType === 'LEND') {
//                 setIntrestRate(selectedDevise.lend);
//             }

//         }
//     }, [selectedDevise,  operationType]); // This will trigger when selectedDevise changes


//     const increment = (step) => {
//         setMontant((prevMontant) => Math.max(0, prevMontant + step)); // Prevent negative values
//     };
//     // Enhanced handleChange function
//     const handleChange = (event) => {
//         const newValue = parseFloat(event.target.value);
//         if (!isNaN(newValue)) {
//              //Check if the value is a valid number
//              				if (parseFloat(newValue) > 10) {
// 					setError("");
// 				} else {
// 					setError("Amount must be more than 10.");
// 				}
//             // Update montant and handle error
//             setMontant(Math.max(0, newValue)); // Set montant and prevent negatives
// //            setError(newValue <= 10 ? "Quantity must be more than 10." : ""); // Set error based on the value
//         }
//     };

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         setError('');
//         setSuccessMessage('');

//         const isoDate = new Date(dateEcheance).toISOString().split("T")[0] + "T00:00:00";
//         console.log("Maturity Date (dateEcheance) before sending:", dateEcheance);

//         console.log("Maturity Date (dateEcheance) before sending:", isoDate);

//         const operationData = {
// //            opnum,
//             type: operationType,
//             amount: montant,
//             maturityDate: isoDate,
//             intrestType,
//             intrestRate,
//             currency: {
//                 label: selectedDevise.label // only sending the label (e.g., "US Dollar")
//             },
//         };

//         try {
//             // Call the addOperation API service
//             const result = await addOperation(operationData);
//             setSuccessMessage('Operation added successfully!');
//             console.log('Added operation:', result);
            
//             // Optionally reset the form fields after successful submission
//              //setOpnum('');
//              //setMontant(10);
//              //setDeviseH('');
//              //console.log(deviseH);
//             // setDateEcheance('');
//             // setDeviseH(id);
//             // console.log(deviseH);

//         } catch (error) {
//             console.error('Error during operation submission:', error);
//             setError('Failed to add operation.'); // Show error message
//         }

//     };
// //    const tradeFees = montant * 0.001; // Trade fees is 1% of quantity

//     return (
//             <div className="addOperation">
//                 {/* open={true} onOpenChange={(isOpen) => { if (!isOpen) onClose(); }} */}
//                 <Dialog.Root open={openDialog} onOpenChange={onClose}> {/* Open the dialog when selectedDevise is set */}
//                 <Dialog.Trigger asChild>
//                     <button className="Button violet">Passer une operation1</button>
//                 </Dialog.Trigger>
//                 <Dialog.Portal>
//                     <Dialog.Overlay className="DialogOverlay"/>
//                     <Dialog.Content className="DialogContent">
//                         <Dialog.Title className="DialogTitle">conduct a financial transaction</Dialog.Title>
//                         <Dialog.Description className="DialogDescription">
//                         Add Operation for {symbol}. Click save when you're done. 
//                         </Dialog.Description>

//                         <form onSubmit={handleSubmit} className="DialogForm">
//                             {/* <div>
//                                 <label>Montant:</label>
//                                 <button type="button" onClick={() => incrementMontant(-1)}>-</button>
//                                 <input
//                                     type="number"
//                                     value={montant}
//                                     onChange={(e) => setMontant(Math.max(0, Number(e.target.value)))}
//                                     required
//                                 />
//                                 <button type="button" onClick={() => incrementMontant(1)}>+</button>
//                             </div> */}


//                             {/* */}
//                              {/* <div className="choisir-devise">
//                             <label htmlFor="deviseChoisie" className="DateLabel">devise choisie</label>
//                                 <input 
//                                     type="number" /*mbaad hotha text ki tbadel fl back */  }
//                                      {/* value={deviseH}
//                                     onChange={(e) => setDeviseH(e.target.value)}
//                                     placeholder="Devise ID" 
//                                     className="devise-input"
//                                     required
//                                 />
//                             </div>  */}
//                             {/** */}
//                             <div className="operation-buttons">
//                                 <button type="button" onClick={() => setOperationType('BORROW')} className="Borrow-button">Borrow {borrow}</button>
//                                 <button type="button" onClick={() => setOperationType('LEND')} className="Lend-button">Lend {lend}</button>
//                             </div>
//                             <div className="choisir-devise">
//                             <label htmlFor="intrestType" className="DateLabel">type d interets</label>     
//                                 <input 
//                                     type="text"
//                                     value={intrestType}
//                                     onChange={(e) => setIntrestType(e.target.value)} // Convert to number
//                                     className="devise-input"
//                                     required
//                                 /> 
//                             </div>
//                             <div className="choisir-devise">
//                             <label htmlFor="intrestRate" className="DateLabel">taux d'intérêts</label>     
//                                 <input 
//                                     type="number"
//                                     value={intrestRate}
//                                     onChange={(e) => setIntrestRate(Number(e.target.value))} // Convert to number
//                                     className="devise-input"
//                                     required
//                                 /> 
//                             </div>


//                             {/** */}
//                             <div className="quantity-selector">
//                             <label htmlFor="montant" className="Amount">Amount</label>
//                                     <button type="button" className="Button-quant" onClick={() => increment(-10)}>-</button>
//                                         <input 
//                                         type="number"
//                                         value={montant}
//                                         onChange={handleChange} 
//                                         className="quantity-input"
//                                         required
//                                         />
//                                     <button type="button" className="Button-quant" onClick={() => increment(10)}>+</button>
//                             </div>
//                             {/** */}
//                             {/* <div className="trade-fees">
//                             <label htmlFor="tradefees" className="TradefeesLabel">fees</label>
//                                     <span className="fees-amount">{tradeFees.toFixed(2)}</span>
//                             </div> */}
//                             {/** */}
//                             <div className="Date-Echeance">
//                                 <label htmlFor="echeanceDate" className="DateLabel">Date Echeance</label>
//                                 <input 
//                                     type="date" 
//                                     id="echeanceDate" 
//                                     onChange={(e) => setDateEcheance(e.target.value)}
//                                     required
//                                     className="Input DateInput" 
//                                     defaultValue="2025-03-27" 
//                                 />
//                             </div>

//                             {/* <div>
//                                 <label>Date Echeance:</label>
//                                 <input
//                                     type="date"
//                                     value={dateEcheance}
//                                     onChange={(e) => setDateEcheance(e.target.value)}
//                                     required
//                                 />
//                             </div> */}

//                             <button type="submit" className="Button green" >Add Operation</button>
//                         </form>
//                             {/* {error && <p style={{ color: 'red' }}>{error}</p>}
//                             {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>} */}
//                             <div
//                                 style={{ display: "flex", marginTop: 5, justifyContent: "flex-end" }}
//                             >
//                         {/* <Dialog.Close asChild>
//                             <button className="Button green">Save changes</button>
//                         </Dialog.Close> */}
//                     </div>
//                     <Dialog.Close asChild>
//                         <button className="IconButton" aria-label="Close">
//                             <Cross2Icon />
//                         </button>
//                     </Dialog.Close>
//                 </Dialog.Content>
//             </Dialog.Portal>
//         </Dialog.Root>
//         </div>
//     );
// };


// export default AddOperationComponent;