import { TabView, TabPanel } from 'primereact/tabview';
import React, { useState } from "react";
import "./PositionPanel.css"; // Import CSS file
import usePanelResizer from "../../hooks/UsePanelResizer";
import PositionTresoD from '../positionTresoD/positionTresoD';
import PositionTresoG from '../positionTresoG/PositionTresoG';
import Operation from '../operation/operation';

const PositionPanel = () => {
    // const [rows, setRows] = useState(initialRows);
   
    const [activeIndex, setActiveIndex] = useState(0);

     //const { panelSize: leftPanelWidth, onMouseDown: onLeftPanelMouseDown } = usePanelResizer(window.innerWidth / 2, "horizontal");
   
     // Calculate the right panel width based on the left panel width
     //const rightPanelWidth = window.innerWidth - leftPanelWidth;

     const { panelSize: leftPanelWidth, onMouseDown: onLeftPanelMouseDown } = usePanelResizer(window.innerWidth / 2, "horizontal");
     // Calculate the right panel width based on the left panel width
     const rightPanelWidth = window.innerWidth - leftPanelWidth;
   
     return (

       <div className="positionPanel">

        <div className="tabbedposition">
        <button className="tab-btn" onClick={() => setActiveIndex(0)}>Positions de Trésorerie</button>
        <button className="tab-btn" onClick={() => setActiveIndex(1)}>Opérations</button>
        </div>

        <div className="card">
            <TabView activeIndex={activeIndex} onTabChange={(e) => setActiveIndex(e.index)}>
                <TabPanel>
                    {/* Left Panel */}
                    <div className="left-panel" style={{ width: `${leftPanelWidth}px` }}>
                        <PositionTresoD />
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
                        <PositionTresoG />
                    </div>
                </TabPanel>
                <TabPanel>
                   <Operation />
                </TabPanel>

        </TabView>            
        </div>

       </div>
     );
   };
   
   export default PositionPanel;
   