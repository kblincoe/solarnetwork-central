import test from 'ava';
import mergeObjects from '../../src/util/mergeObjects'

test('util:mergeObjects:copy', t => {
	const dest = {};
	const result = mergeObjects(dest, {a:1, b:2, c:3});
	t.is(result, dest, 'The return value is the same as the first argument.');
	t.deepEqual(result, {a:1, b:2, c:3});
});

test('util:mergeObjects:defaultPrecision', t => {
	const result = mergeObjects({}, {a:1.2345});
	t.deepEqual(result, {a:1.235});
});

test('util:mergeObjects:customPrecision', t => {
	const result = mergeObjects({}, {a:1.2345}, 10);
	t.deepEqual(result, {a:1.2});
});

test('util:mergeObjects:nonNumberValues', t => {
	const src = {a:'foo', b:[1,2,3], c:{see:'there'}};
	const result = mergeObjects({}, src);
	t.deepEqual(result, src);
});
