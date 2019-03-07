var webpack = require('webpack');
var path = require('path')
const TerserPlugin = require('terser-webpack-plugin');

module.exports = {
  // context: path.resolve(__dirname, 'my'),
 //   entry: './src/index.js',
	entry: {
		index: './src/index.js',
		// login: './src/main/js/components/login/index.js',
	},
  output: {
		// path: __dirname + '/public/',
  	path: __dirname + '/../src/main/resources/static/built/',
		// path: path.resolve(__dirname, 'dist'),
    // filename: 'bundle.js'
		filename: '[name].bundle.js',
		publicPath: '/built/',
		chunkFilename: '[name].bundle.js',
  },
	optimization: {
		// minimizer: [new TerserPlugin()]
	  minimizer: [
			new TerserPlugin()
		],
	},
	mode: 'development',
  devtool: 'inline-source-map',
  module: {
  	rules: [
      {
        test: /\.js$/,
				use: {
          loader: "babel-loader",
					options: {
						presets: [
							'@babel/preset-env',
							'@babel/preset-react',
		          // '@babel/preset-stage-0'
							// 'module:@babel/plugin-proposal-decorators',
						],
						plugins: [
							// "@babel/plugin-transform-runtime",
							// ["@babel/preset-stage-2", { decoratorsBeforeExport: true }],
							["@babel/plugin-proposal-decorators", { legacy: true }],
							["@babel/plugin-proposal-class-properties", { loose: true }],
							// ["babel-plugin-styled-components"],
							["@babel/plugin-syntax-dynamic-import"]
						],
					}
        },
      },
      {
      	test: /\.(woff|woff2|eot|ttf|svg)$/,
				use: [{
					loader: 'file-loader',
					options: {
						name: '[name].[ext]',
						publicPath: '/built/fonts',
						outputPath: '/fonts/', // => /built/fonts
					}
				}],
        // loader: 'file-loader?name=fonts/[name].[ext]'
      },
			{
        test: /\.scss$/,
        use: [
					{ loader: "style-loader" }, // creates style nodes from JS strings
          { loader: "css-loader" }, // translates CSS into CommonJS
					{ loader: "sass-loader" } // compiles Sass to CSS
        ]
			},
			{
				test: /\.css$/,
				// loader: "style-loader!css-loader"
				use: ['style-loader', 'css-loader'],
			},
    ]
  },
  plugins: [
  ]
};
