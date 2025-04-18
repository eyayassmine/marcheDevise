import React, { useEffect, useState, useRef } from 'react';
import Chart from 'chart.js/auto';
import { fetchForexStream } from "../../../../services/devise/StreamDevise";

const BorrowLendChart =  ( {selectedDeviseChart} ) => {

    const { id, label, symbol, borrow, lend, colorBorrow, colorLend } = selectedDeviseChart || {};  // Ensure selectedDevise is valid
    const chartRef = useRef(null);
    const chartInstanceRef = useRef(null);
    const [deviseData, setDeviseData] = useState([]);  // State to store unique forex data

    useEffect(() => {
        if (selectedDeviseChart) {
            console.log("hehee");

            console.log("he", id);
            console.log("he", label);
            console.log("he", symbol);
            console.log("he", borrow);
            console.log("he", lend);
            console.log("colorBorrow ", colorBorrow);
            console.log("colorLend ", colorLend);
        }
    }, [selectedDeviseChart]); // This will trigger when selectedDevise changes
 

    useEffect(() => {
      const ctx = chartRef.current.getContext('2d');
  
      // Init Chart
      chartInstanceRef.current = new Chart(ctx, {
        type: 'line',
        data: {
          labels: [],
          datasets: [
            {
              label: 'Borrow Rate',
              data: [],
              borderColor: colorBorrow || 'rgba(255, 99, 132, 1)',
              fill: false,
            },
            {
              label: 'Lend Rate',
              data: [],
              borderColor: colorLend || 'rgba(54, 162, 235, 1)',
              fill: false,
            }
          ]
        },
        options: {
          responsive: true,
          animation: false,
          scales: {
            x: {
              title: {
                display: true,
                text: 'Time (per second)'
              }
            },
            y: {
              title: {
                display: true,
                text: 'Rate'
              }
            }
          }
        }
      });
  
      // Set up SSE
    const eventSource = fetchForexStream(setDeviseData, deviseData);
      //    const eventSource = fetchForexStream(setDeviseData, deviseData);
  

      eventSource.onmessage = function (event) {
        // const data = JSON.parse(event.data);
        // const filtered = data.find(forex => forex.label === label);
        // console.log("the label her of forex hereRateHLine ", forex.label);
        // const chart = chartInstanceRef.current;
        const deviseData = JSON.parse(event.deviseData);
        const filtered = deviseData.find(forex => forex.label === label);
        console.log("the label her of forex hereRateHLine ", forex.label);
        const chart = chartInstanceRef.current;
 
        

        if (filtered && chart) {
          const now = new Date().toLocaleTimeString();
  
          /*if (chart.data.labels.length > 30) {
            chart.data.labels.shift();
            chart.data.datasets[0].data.shift();
            chart.data.datasets[1].data.shift();
          }*/
  
          chart.data.labels.push(now);
          chart.data.datasets[0].data.push(filtered.borrow);
          chart.data.datasets[1].data.push(filtered.lend);
          chart.update();
          console.log("label", label);
          console.log("label", filtered.label);
          console.log("borrow", filtered.borrow);
          console.log("sborrow", filtered.soborrow);
          console.log("lend", filtered.lend);
          console.log("slend", filtered.solend);

        }
      };
  
      // Cleanup
      return () => {
        eventSource.close();
        chartInstanceRef.current.destroy();
      };
    }, [selectedDeviseChart]);
  
    return (
      <div>
        <canvas ref={chartRef} width={600} height={300}></canvas>
      </div>
    );
  };
  
  export default BorrowLendChart;




// 
// import { AgChartsReact } from "ag-charts-react";
// import { useMemo } from "react";

// const RateHStatistics = ({ rateHistory, selectedLabel }) => {
//   const data = useMemo(() => {
//     return rateHistory
//       .filter((entry) => entry.label === selectedLabel)
//       .map((entry) => ({
//         date: new Date(entry.lastUpdated),
//         sborrow: entry.sborrow,
//         slend: entry.slend,
//       }));
//   }, [rateHistory, selectedLabel]);

//   const options = {
//     title: {
//       text: `Borrow & Lend Statistics for ${selectedLabel}`,
//     },
//     data,
//     container: document.getElementById("chartContainer"), // if using AGCharts directly, not ag-charts-react
//     series: [
//       {
//         type: "line",
//         xKey: "date",
//         yKey: "sborrow",
//         yName: "Borrow",
//         stroke: "#007bff",
//       },
//       {
//         type: "line",
//         xKey: "date",
//         yKey: "slend",
//         yName: "Lend",
//         stroke: "#28a745",
//       },
//     ],
//     axes: [
//       {
//         position: "bottom",
//         type: "time",
//         title: { text: "Time" },
//       },
//       {
//         position: "left",
//         type: "number",
//         title: { text: "Rate" },
//       },
//     ],
//   };

//   return <AgChartsReact options={options} />;
// };
