import React, { useState, useEffect } from "react";
import "./DeviseStat.css"; // Import CSS file
import DeviseBarChart from "./devise-bar-charts/DeviseBarChart";





const DeviseStat = () => {


  const [zoom, setZoom] = useState(1); // State to control zoom level

  const zooming = () => {
    setZoom(prevZoom => Math.max(prevZoom + 0.2, 0.5)); // Prevent zooming out too much
  };

  return (
    <div className="deviseStat" >

        <div className="chart-config-buttons">
          <button className="range-preriod" title="Choose your period range">Range Period</button>
          <button className="chart-type-choice"  title="Choose a chart type">📊 Chart Type</button>
          <button className="chart-screenshot"  title="take a screenshot of the chart">📸</button>
          <button className="take-position"  title="take a position"> take a position</button>
          <button className="zoom-in" onClick={zooming}>Zoom In</button>
        </div>
        <div className = "chartdashbord" style={{ transform: `scale(${zoom})`, transformOrigin: "top left" }}>
        <DeviseBarChart />
        </div>
    </div>
  );
};

export default DeviseStat;