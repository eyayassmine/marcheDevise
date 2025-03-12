import React, { useState, useEffect } from "react";
import "./DeviseBarChart.css"; // Import CSS file
import { Bar } from "react-chartjs-2";
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement } from "chart.js";

// Register necessary Chart.js components
ChartJS.register(CategoryScale, LinearScale, BarElement);
/*
const columns = [
  { columnId: "symbol", width: 100, title: "Devise" },
  { columnId: "interet emprunt", width: 100, title: "Interet emprunt" },
  { columnId: "interet pret", width: 100, title: "interet pret" },
  { columnId: "moyenne des interets", width: 100, title: "Moyenne des interets" },
  { columnId: "spread", width: 100, title: "Spread" }
];

const initialRows = [
  { rowId: 1, cells: [{ type: "text", text: "EUR"}, { type: "text", text: "3.5%"}, { type: "text", text: "+2.5%"}, { type: "text", text: "1.2M"}, { type: "text", text: "1.2M"}] },
  { rowId: 2, cells: [{ type: "text", text: "CAD"}, { type: "text", text: "1.8%"}, { type: "text", text: "1.3%"}, { type: "text", text: "500K"}, { type: "text", text: "1.2M"}] },
  { rowId: 3, cells: [{ type: "text", text: "JPY"}, { type: "text", text: "5%"}, { type: "text", text: "4.8%"}, { type: "text", text: "900K"}, { type: "text", text: "1.2M"} ]},
  { rowId: 4, cells: [{ type: "text", text: "GPB"}, { type: "text", text: "6.4%"}, { type: "text", text: "5.5%"}, { type: "text", text: "2M"}, { type: "text", text: "1.2M"}] },
  { rowId: 5, cells: [{ type: "text", text: "AUD"}, { type: "text", text: "9.4%"}, { type: "text", text: "8.1%"}, { type: "text", text: "1.1M"}, { type: "text", text: "1.2M"}] },
  { rowId: 6, cells: [{ type: "text", text: "USD"}, { type: "text", text: "7.2%"}, { type: "text", text: "6.6%"}, { type: "text", text: "1.1M"}, { type: "text", text: "1.2M"}] },
  { rowId: 7, cells: [{ type: "text", text: "SGD"}, { type: "text", text: "4.3%"}, { type: "text", text: "3.9%"}, { type: "text", text: "1.1M"}, { type: "text", text: "025"}] }

];*/

const DeviseBarChart = () => {

/*  const [size, setSize] = useState(initialSize);
  useEffect(() => {
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


  const data = {
    labels: ["A", "B", "C"],
    datasets: [
      {
        label: "Taux d'emprunt",
        data: [0.75, 7, 6],
        borderColor: '#36A2EB',
        backgroundColor: '#d2AC47',
        // Additional customization like backgroundColor can be added here
      },
      {
        label: "Taux de prêt",
        data: [0.64, 6.7, 5.3],
        borderColor: '#FF6384',
        backgroundColor: '#ccc',
        // Additional customization like backgroundColor can be added here
      },
    ],
  };


  // Définissez les options du graphique
  const options = {
    responsive: true,
    scales: {
      x: {
        beginAtZero: true,
      },
      y: {
        beginAtZero: true,
      },
    },
  };

  return (
    <div className="deviseBarChart " >
      <div className="chart-container">
        <Bar data={data} options={options} />
      </div>
    </div>
  );
};

export default DeviseBarChart;
