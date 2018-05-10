window.onload = function() {
	
	function checkRow(arr,i,j) {
		var a = [0,0,0,0,0,0,0,0,0];
		arr[i].forEach(function(value) {
			a[value-1]++;
		});
		return a.every(function(v) { return v<2; });
	}

	function checkCol(arr,i,j) {
		var a  = [0,0,0,0,0,0,0,0,0];
		arr.forEach(function(row) {
			a[row[j]-1]++;
		});
		return a.every(function(v) { return v<2; });
	}

	function checkBox(arr,i,j) {
		var relI = i - i%3,
			relJ = j - j%3,
			a = [0,0,0,0,0,0,0,0,0];
		[0,1,2].forEach(function(iOffset) {
			[0,1,2].forEach(function(jOffset) {
				a[arr[relI+iOffset][relJ+jOffset]-1]++;
			});
		});
		return a.every(function(v) { return v<2; });
	}

	function solve(arr) {
		var empI = null,
			empJ = null,
			sol;
		arr.forEach(function(row,i) {
			row.forEach(function(v,j) {
				empI = (empI == null ? (v ? null : i) : empI);
				empJ = (empJ == null ? (v ? null : j) : empJ);
			});
		});
		if (empI == null && empJ == null) {
			return arr;
		}
		var newArr = JSON.parse(JSON.stringify(arr));
		newArr[empI][empJ] = 1;
		while (newArr[empI][empJ] <= 9 &&
				(!checkRow(newArr,empI,empJ) ||
				!checkCol(newArr,empI,empJ) ||
				!checkBox(newArr,empI,empJ) ||
				!(sol = solve(newArr)))) {
			newArr[empI][empJ]++;
		}
		return sol;
	}

	document.getElementsByTagName('button')[0].onclick = function() {
		var arr = [],
			row = [],
			inputs = document.getElementsByTagName('input'),
			sol;
		[].forEach.call(inputs, function(input) {
			row.push(input.value);
			if (row.length == 9) {
				arr.push(row);
				row = [];
			}
		});
		sol = solve(arr);
		if (sol) {
			sol.forEach(function(row,i) {
				row.forEach(function(v,j) {
					inputs[i*9 + j].value = v;
				});
			});
		}
	};
};