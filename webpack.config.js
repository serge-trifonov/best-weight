const path = require('path');
const VueLoaderPlugin = require('vue-loader/lib/plugin');
module.exports = {
  mode: 'development',
  devtool: 'source-map',
  entry: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'main.js'),
  devServer: {
    contentBase: './dist',
    compress: true,
    port: 8000,
    allowedHosts: [
      'localhost:8080'
    ],
    stats: 'errors-only',
    clientLogLevel: 'error',
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      {
        test: /\.(scss)$/,
        use: [{
          loader: 'style-loader',
        }, {
          loader: 'css-loader',
        }, {
          loader: 'postcss-loader',
          options: {
            plugins: function () { 
              return [
                require('precss'),
                require('autoprefixer')
              ];
            }
          }
        }, {
          loader: 'sass-loader' 
        }]
      },
	  {
        test: /\.css$/i,
        use: ['to-string-loader', 'css-loader'],
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },
	  {
        test: /\.(png|jpe?g|gif)$/i,
        loader: 'file-loader',
        options: {
          name: '[path][name].[ext]',
      }
	 },
	 {
        test: /\.svg/,
        use: {
          loader: "svg-url-loader",
          options: {}
        }
	 },
	 {
        test: /\.(ttf|otf|eot|woff|woff2)$/,
        use: {
          loader: "file-loader",
          options: {
            name: "fonts/[name].[ext]",
          },
        },
      }
    ]
  },
  plugins: [
    new VueLoaderPlugin()
  ],
  resolve: {
	  extensions: ['*', '.js', '.vue', '.json'],   
      alias: {
      	'vue$': 'vue/dist/vue.esm.js'
      },
      modules: [
          path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
          path.join(__dirname, 'src', 'main', 'resources', 'static', 'images'),
          path.join(__dirname, 'node_modules'),
      ],
  }
}