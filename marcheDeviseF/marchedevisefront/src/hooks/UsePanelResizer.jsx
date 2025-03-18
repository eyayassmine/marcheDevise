import { useState, useRef, useCallback } from "react";

const UsePanelResizer = (initialSize = 300, direction = "horizontal") => {
  const [panelSize, setPanelSize] = useState(initialSize);
  const isResizing = useRef(false);
  const startPos = useRef(0);

  const onMouseMove = useCallback(
    (e) => {
      if (isResizing.current) {
        // Horizontal resizing (x-axis)
        if (direction === "horizontal") {
          const newWidth = panelSize + (e.clientX - startPos.current);
          setPanelSize(Math.max(newWidth, 100)); // Prevent shrinking too much
        }
        // Vertical resizing (y-axis)
        else if (direction === "vertical") {
          const newHeight = panelSize + (e.clientY - startPos.current);
          setPanelSize(Math.max(newHeight, 100)); // Prevent shrinking too much
        }
      }
    },
    [panelSize, direction]
  );

  const onMouseUp = useCallback(() => {
    isResizing.current = false;
    document.removeEventListener("mousemove", onMouseMove);
    document.removeEventListener("mouseup", onMouseUp);
  }, [onMouseMove]);

  const onMouseDown = (e) => {
    isResizing.current = true;
    startPos.current = direction === "horizontal" ? e.clientX : e.clientY;

    document.addEventListener("mousemove", onMouseMove);
    document.addEventListener("mouseup", onMouseUp);
  };

  return { panelSize, onMouseDown, setPanelSize };
};

export default UsePanelResizer;
