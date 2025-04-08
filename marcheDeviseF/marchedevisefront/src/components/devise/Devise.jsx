import React, { useState, useRef, useCallback } from "react";
import "./Devise.css"; // Import CSS file
import DeviseList from "./deviseList/DeviseList";
import DeviseBarChart from "./deviseStats/devise-bar-charts/DeviseBarChart";
import usePanelResizer from "../../hooks/UsePanelResizer";
import DeviseStat from "./deviseStats/deviseStat";
import DeviseListStream from "./deviseList/DeviseListStream";
//import { Splitter, SplitterPanel } from 'primereact/splitter';
//import { PaneDirective, PanesDirective, SplitterComponent } from '@syncfusion/ej2-react-layouts';



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

const Workspace = () => {

  const { panelSize: leftPanelWidth, onMouseDown: onLeftPanelMouseDown } = usePanelResizer(window.innerWidth / 2, "horizontal");

  // Calculate the right panel width based on the left panel width
  const rightPanelWidth = window.innerWidth - leftPanelWidth;

  return (
    <div className="workspace">

      
      {/* Left Panel */}
      <div className="left-panel" style={{ width: `${leftPanelWidth}px` }}>
        {/* <DeviseListStream /> */}
        <DeviseListStream />
      </div>
      {/* Resizable Splitter */}
      <div
        className="workspace-splitter"
        onMouseDown={onLeftPanelMouseDown}
        style={{ cursor: "ew-resize" }}
      />

      {/* Right Panel */}
      <div
        className="right-panel"
        style={{
          flexGrow: 1,
          width: `${rightPanelWidth}px`, // Dynamically adjust the width of the right panel
        }}
      >
        <DeviseStat />
        {/*<DeviseBarChart />*/}
      </div>


    </div>
  );
};

export default Workspace;


/////splitter withou hook
 // const [rows, setRows] = useState(initialRows);

  //const [zoom, setZoom] = useState(1); // State to control zoom level

  /*const zoomOut = () => {
    setZoom(prevZoom => Math.max(prevZoom - 0.2, 0.5)); // Prevent zooming out too much
  };*/

  // const [leftPanelWidth, setLeftPanelWidth] = useState(window.innerWidth / 2);
  // const isResizing = useRef(false); // To track if the user is resizing
  // const startX = useRef(0); // To store the starting mouse position

  // const onMouseMove = useCallback(
  //   (e) => {
  //     if (isResizing.current) {
  //       // Calculate the new width of the left panel based on mouse movement
  //       const newWidth = leftPanelWidth + (e.clientX - startX.current);
  //       setLeftPanelWidth(Math.max(newWidth, 100)); // Prevent shrinking too much
  //     }
  //   },
  //   [leftPanelWidth]
  // );

  // const onMouseUp = useCallback(() => {
  //   // Remove mousemove and mouseup listeners when resizing ends
  //   isResizing.current = false;
  //   document.removeEventListener("mousemove", onMouseMove);
  //   document.removeEventListener("mouseup", onMouseUp);
  // }, [onMouseMove]);

  // const onMouseDown = (e) => {
  //   // Track the initial mouse position when the user starts dragging
  //   isResizing.current = true;
  //   startX.current = e.clientX;

  //   // Add event listeners to handle mouse move and mouse up
  //   document.addEventListener("mousemove", onMouseMove);
  //   document.addEventListener("mouseup", onMouseUp);
  // };
  // // Calculate the width for the right panel based on the left panel width
  // const rightPanelWidth = window.innerWidth - leftPanelWidth;



      /*<Splitter style={{ height: '100vh' }} layout="horizontal">
        <SplitterPanel size={50}>
          <div className="left-panel">
            <DeviseList />
          </div>
        </SplitterPanel>
        <SplitterPanel size={50}>
          <div className="right-panel">
            <DeviseBarChart />
          </div>
        </SplitterPanel>
      </Splitter>*/