import React, { useState, useEffect } from "react";
import { getAllOperations } from "../../services/operation/operation";
import "./Operation.css"; // Import CSS file


const columns = [
  { columnId: "devise", width: 100, title: "Devise" },
  { columnId: "ticket", width: 100, title: "Ticket" },
  { columnId: "type", width: 100, title: "Type" },
  { columnId: "montant", width: 100, title: "Montant" },
  { columnId: "operationDate", width: 100, title: "Date d'operation" },
  { columnId: "maturityDate", width: 100, title: "Date Echeance" },
  { columnId: "intrestRate", width: 100, title: "Taux d'interets" },
  { columnId: "maturityRate", width: 100, title: "Taux d'intérêts d'échéance" },
  { columnId: "intrestType", width: 100, title: "Type d'intérêts" },
  { columnId: "maturityAmout", width: 100, title: "montant d'échéance" },
  { columnId: "givedAmount", width: 100, title: "montant donné" }  ];

const initialRows = [
  { rowId: 1, cells: [{ type: "text", text: "EUR"}, { type: "text", text: "1"}, { type: "text", text: "180000"}, { type: "text", text: "Emprunt"}, { type: "text", text: "12/02/2024"}, { type: "text", text: "12/02/2025"}] },
  { rowId: 2, cells: [{ type: "text", text: "CAD"}, { type: "text", text: "2"}, { type: "text", text: "560000"}, { type: "text", text: "Emprunt"}, { type: "text", text: "24/03/2024"}, { type: "text", text: "12/02/2025"}] },
  { rowId: 3, cells: [{ type: "text", text: "JPY"}, { type: "text", text: "3"}, { type: "text", text: "5400000"}, { type: "text", text: "Pret"}, { type: "text", text: "25/06/2024"}, { type: "text", text: "12/02/2025"}] },
  { rowId: 4, cells: [{ type: "text", text: "GPB"}, { type: "text", text: "4"}, { type: "text", text: "5480000"}, { type: "text", text: "Emprunt"}, { type: "text", text: "30/08/2024"}, { type: "text", text: "12/02/2025"}] },
  { rowId: 5, cells: [{ type: "text", text: "AUD"}, { type: "text", text: "5"}, { type: "text", text: "680000"}, { type: "text", text: "Pret"}, { type: "text", text: "14/10/2024"}, { type: "text", text: "12/02/2025"}] },
  { rowId: 6, cells: [{ type: "text", text: "USD"}, { type: "text", text: "6"}, { type: "text", text: "520000"}, { type: "text", text: "Pret"}, { type: "text", text: "12/02/2025"}, { type: "text", text: "12/02/2025"}] },
  { rowId: 6, cells: [{ type: "text", text: "USD"}, { type: "text", text: "6"}, { type: "text", text: "700000"}, { type: "text", text: "Emprunt"}, { type: "text", text: "18/02/2025"}, { type: "text", text: "12/02/2025"}] },
  { rowId: 6, cells: [{ type: "text", text: "USD"}, { type: "text", text: "6"}, { type: "text", text: "700000"}, { type: "text", text: "Emprunt"}, { type: "text", text: "12/03/2025"}, { type: "text", text: "12/02/2025"}] },
  { rowId: 7, cells: [{ type: "text", text: "SGD"}, { type: "text", text: "7"}, { type: "text", text: "3500000"}, { type: "text", text: "Pret"}, { type: "text", text: "12/03/2025"}, { type: "text", text: "12/02/2025"}] }

];


const Operation = () => {

/*  useEffect(() => {
    const handleMouseMove = (e) => {
      const movement = e.movementX;
      setSize(size + movement);
    };

    document.addEventListener("mousemove", handleMouseMove);
    return () => {
      document.removeEventListener("mousemove", handleMouseMove);
    };
  }, [size]); 
  style={{ width: `${size}px` }}
  */
  //const [rows, setRows] = useState(initialRows);

  const [rows, setRows] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);


  useEffect(() => {
    const displayoperations = async () => {
      try {
        const data = await getAllOperations();
        const formattedRows = data.map((operation) => ({
          currency: operation.currency.label,
          id: operation.id,
          type: operation.type,
          amount: operation.amount,
          createdDate: operation.operationDate,
          maturityDate: operation.maturityDate,
          intrestRate: operation.intrestRate,
          maturityRate: operation.maturityRate,
          intrestType: operation.intrestType,
          maturityAmount: operation.maturityAmount,
          givedAmount: operation.givedAmount,
        }));
        setRows(formattedRows);
      } catch (err) {
        setError("Failed to load data.");
      } finally {
        setLoading(false);
      }
    };


    displayoperations();
  }, []);

  return (
    <div className="operation" >
    
                        <div className="small-text">Liste des positions de Trésorerie Détaillée</div>
                <div className="filterbuttons">
                <button className="filter-btn">Filter 1</button>
                <button className="filter-btn">Filter 2</button>
                <button className="filter-btn">Filter 3</button>
                </div>
                        <div className="inner-part">
                                <div className="grid-containertl">
                                    <div className="overflow-auto max-h-64 border border-gray-300 rounded-lg">
                                        <table className="w-full border-collapse">
                                            {/* Table Head */}
                                            <thead className="devisetablehead">
                                                <tr>
                                                {columns.map((col) => (
                                                    <th key={col.columnId} className="p-2 border border-gray-600">
                                                    {col.title}
                                                    </th>
                                                ))}
                                                </tr>
                                            </thead>
                                            
                                            {/* Table Body */}
                                            <tbody>
                                              {rows.map((row) => (
                                                <tr key={row.id} className="bg-gray-100">
                                                  <td className="p-2 border border-gray-400 text-center">{row.currency}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.id}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.type}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.amount}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.createdDate}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.maturityDate}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.intrestRate}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.maturityRate}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.intrestType}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.maturityAmount}</td>
                                                  <td className="p-2 border border-gray-400 text-center">{row.givedAmount}</td>
                                                </tr>
                                              ))}
                                            </tbody>
                                            {/* <tbody>
                                                {rows.map((row, rowIndex) => (
                                                <tr key={rowIndex} className={rowIndex % 2 === 0 ? "bg-gray-100" : "bg-white"}>
                                                    {row.cells.map((cell, colIndex) => (
                                                    <td key={colIndex} className="p-2 border border-gray-400 text-center">
                                                        {cell.text}
                                                    </td>
                                                    ))}
                                                </tr>
                                                ))}
                                            </tbody> */}
                                        </table>
                                    </div>
                                </div>
                        </div>
    
    </div>

  );
};

export default Operation;