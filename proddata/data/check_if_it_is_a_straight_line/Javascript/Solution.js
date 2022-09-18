class Solution {
  checkStraightLine = function (coordinates) {
    let dy, dx, ndy, ndx, i;
    dy = coordinates[1][1] - coordinates[0][1];
    dx = coordinates[1][0] - coordinates[0][0];

    for (i = 2; i < coordinates.length; i++) {
      ndy = coordinates[i][1] - coordinates[0][1]; // new dy
      ndx = coordinates[i][0] - coordinates[0][0]; // new dx

      //straight line only if: (x3 - x1)/ (y3 - y1) == (x2 - y1)/ (y2 - y1)
      if (ndx * dy !== ndy * dx) return false;
    }

    return true;
  };
}

module.exports = Solution;
